package lindar.acolyte.vo

data class PaginatedCollection<T> (val contents: List<T>, val pagination: PaginationVO, val sort: SortVO)

data class PaginationVO (val page: Int, val size: Int, val totalPages: Int, val totalElements: Long) {
    companion object Builder {
        private var page = 0
        private var size = 20
        private var totalPages = 0
        private var totalElements = 0L

        fun page(page: Int): PaginationVO.Builder {
            this.page = page
            return this
        }

        fun size(size: Int): PaginationVO.Builder {
            this.size = size
            return this
        }

        fun totalPages(totalPages: Int): PaginationVO.Builder {
            this.totalPages = totalPages
            return this
        }

        fun totalElements(totalElements: Long): PaginationVO.Builder {
            this.totalElements = totalElements
            return this
        }


        fun build() = PaginationVO(page, size, totalPages, totalElements)
    }
}

data class PageableVO (val page: Int, val size: Int, val sort: List<SortVO>? = null) {
    companion object Builder {
        private var page = 0
        private var size = 20
        private var sort: List<SortVO>? = null

        fun page(page: Int): PageableVO.Builder {
            this.page = page
            return this
        }

        fun size(size: Int): PageableVO.Builder {
            this.size = size
            return this
        }

        fun sort(sort: List<SortVO>): PageableVO.Builder {
            this.sort = sort
            return this
        }

        fun build() = PageableVO(page, size, sort)
    }
}

data class SortVO (val field: String, val dir: SortDirection = SortDirection.ASC) {
    companion object Builder {
        private lateinit var field: String
        private var dir = SortDirection.ASC

        fun field(field: String): SortVO.Builder {
            this.field = field
            return this
        }

        fun dir(dir: SortDirection): SortVO.Builder {
            this.dir = dir
            return this
        }

        fun build() = SortVO(field, dir)
    }
}


enum class SortDirection {
    DESC, ASC
}