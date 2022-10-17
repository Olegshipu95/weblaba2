package itmo.web2.weblab2.model

class CollectionWithDataPoints {
    var collectionWithDataPoints = mutableListOf<DataWithPoints>()

    class DataWithPoints (var x: Double, var y: Double, var r: Double, var entry: String, var timeOfScript: String){}
}