package com.pnam.watchingsocceronline.data.utils

enum class Filter {
    LIKE {
        override fun toString(): String {
            return "like"
        }
    },
    DISLIKE {
        override fun toString(): String {
            return "dislike"
        }
    },
    VIEW {
        override fun toString(): String {
            return "view"
        }
    };
}