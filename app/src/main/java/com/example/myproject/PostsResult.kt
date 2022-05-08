package com.example.myproject


data class PostsResult (
    var id : String,
    var image : String,
    var tags : Array<String>,
    var text : String,
    var publishDate : String ,
    var owner : Owner,
        )


data class Owner (
    var id : String,
    var title : String ,
    var firstName : String ,
    var lastName : String ,
    var picture : String ,
)