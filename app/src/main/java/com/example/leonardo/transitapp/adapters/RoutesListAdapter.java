package com.example.leonardo.transitapp.adapters;

/**
 * Created by leonardo on 24/10/17.
 */

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leonardo.transitapp.R;
import com.example.leonardo.transitapp.model.Price;
import com.example.leonardo.transitapp.model.Route;
import com.example.leonardo.transitapp.model.Segment;
import com.example.leonardo.transitapp.utils.AdapterDuties;

import java.util.List;

public class RoutesListAdapter extends RecyclerView.Adapter<RoutesListAdapter.RouteViewHolder> {

    private List<Route> routes;
    private List<String> timeData;
    private RouteListener routeListener;
    private AdapterDuties adapterDuties = new AdapterDuties();

    public RoutesListAdapter(List<Route> routes, RouteListener routeListener) {
        this.routes = routes;
        this.routeListener = routeListener;
    }

    public static class RouteViewHolder extends RecyclerView.ViewHolder {

        private TextView routeType;
        private TextView routeCurrency;
        private TextView routePrice;
        private TextView routeStartTime;
        private TextView routeEndTime;
        private TextView routeTotalTime;
        private ImageView routeImage;
        private CardView routeCardView;

        public RouteViewHolder(View v) {
            super(v);

            routeType = itemView.findViewById(R.id.tv_route_type);
            routeCurrency = itemView.findViewById(R.id.tv_route_currency);
            routePrice = itemView.findViewById(R.id.tv_route_price);
            routeStartTime = itemView.findViewById(R.id.tv_start_time);
            routeEndTime = itemView.findViewById(R.id.tv_end_time);;
            routeTotalTime = itemView.findViewById(R.id.tv_route_total_time);
            routeImage = itemView.findViewById(R.id.iv_route_image);
            routeCardView = itemView.findViewById(R.id.cv_item_route);
        }
    }

    @Override
    public RouteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_route, parent, false);

        return new RouteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RouteViewHolder holder, int position) {

        final Route route = routes.get(position);
        String routeType;

        routeType = route.getType();
        holder.routeType.setText(routeType);

        Price price = route.getPrice();
        if (price != null) {

            String currency = adapterDuties.getPriceCurrency(price.getCurrency());
            String amount = adapterDuties.getPriceAmount(price.getAmount());
            holder.routeCurrency.setText(currency);
            holder.routePrice.setText(amount);
        }

        List<Segment> segment = route.getSegments();
        if(segment != null){
            timeData = adapterDuties.getRouteTimeInfo(segment);
            holder.routeTotalTime.setText(timeData.get(0));
            holder.routeStartTime.setText(timeData.get(1));
            holder.routeEndTime.setText(timeData.get(2));
        }
        setResources(holder, routeType);            //set Image and title

        holder.routeCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                routeListener.onClickRoute(route);
            }
        });
    }
        //set image and text
    public void setResources(RouteViewHolder holder, String routeType){

        if(routeType != null) {
            switch (routeType) {
                case "public_transport":
                    holder.routeImage.setImageResource(R.mipmap.im_public_transport);
                    holder.routeType.setText(R.string.routes_type_public_transport);
                    break;
                case "bike_sharing":
                    holder.routeImage.setImageResource(R.mipmap.im_bike_sharing);
                    holder.routeType.setText(R.string.routes_type_bike_sharing);
                    break;
                case "car_sharing":
                    holder.routeImage.setImageResource(R.mipmap.im_car_sharing);
                    holder.routeType.setText(R.string.routes_type_car_sharing);
                    break;
                case "private_bike":
                    holder.routeImage.setImageResource(R.mipmap.im_private_bike);
                    holder.routeType.setText(R.string.routes_type_private_bike);
                    break;
                case "taxi":
                    holder.routeImage.setImageResource(R.mipmap.im_taxi);
                    holder.routeType.setText(R.string.routes_type_taxi);
                    break;
                default:        //in case of no image in res for that routeType
                    holder.routeImage.setImageResource(R.mipmap.im_image_not_found);
            }
        }
    }

    public void notifyData(List<Route> routeList) {
        this.routes = routeList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return routes.size();
    }

    public interface RouteListener {
        void onClickRoute(Route route);
    }
}
