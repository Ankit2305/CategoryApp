package com.example.categoryapp.models

class Category(val id: Int, val name: String, val parent_id: Int, val subCategory: Array<SubCategory>?)

class SubCategory(val id: Int, val name: String, val parent_id: Int)

//{
//    id: 586,
//    name: "Cake by Flavour",
//    parent_id: 0,
//    subCategory: [
//    {
//        id: 594,
//        name: "Chocolate Cakes",
//        parent_id: 586
//    },
//    {
//        id: 595,
//        name: "Pineapple & Fruit Cakes",
//        parent_id: 586
//    },
//    {
//        id: 596,
//        name: "other cakes",
//        parent_id: 586
//    }
//    ]
//}