package com.pnam.watchingsocceronline.data.utils

enum class Filter {
    LIKE {
        override fun toString(): String {
            return "Like"
        }
    },
    COMMENT {
        override fun toString(): String {
            return "Comment"
        }
    },
    VIEW {
        override fun toString(): String {
            return "View"
        }
    };
}