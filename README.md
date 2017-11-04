This app emulates the response for a request of routes and mode of transport, then
after a route selection shows the route and soubroutes with different colors on a
map adapted to the max zoom possible due the routes.

- the info is consumed from a JSON file, adapted to a recycler view inflated to a fragment
and finally according to the selection show the route in a map in another fragment.
- MVP is used as a design pattern.

the project supports english/spanish and adapts to different screensizes and screen desities

main libraries used:
-gson
-retrofit
-rxJava

Bugs founded:
-in the JSON the price comes as a xxx value, should be explicit x.xx
(can't know for instance the diference between €1.00 and €10.0)
-null values (handled)

TODO: with more time I would have added:
-data binding
-Unit test
-Bottom Sheet for the route info
-Splash activity
-marker label start/end route
