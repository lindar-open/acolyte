package lindar.acolyte.vo

data class PaginatedCollection<out T>(val contents: List<T>, val pagination: PaginationVO, val sort: SortVO)

data class PaginationVO(val page: Int, val size: Int, val totalPages: Int, var totalElements: Long) {
    companion object Builder {
        private var builderPage = 0
        private var builderSize = 20
        private var builderTotalPages = 0
        private var builderTotalElements = 0L

        fun page(page: Int): PaginationVO.Builder {
            this.builderPage = page
            return this
        }

        fun size(size: Int): PaginationVO.Builder {
            this.builderSize = size
            return this
        }

        fun totalPages(totalPages: Int): PaginationVO.Builder {
            this.builderTotalPages = totalPages
            return this
        }

        fun totalElements(totalElements: Long): PaginationVO.Builder {
            this.builderTotalElements = totalElements
            return this
        }


        fun build() = PaginationVO(builderPage, builderSize, builderTotalPages, builderTotalElements)
    }
}

data class PageableVO(val page: Int, val size: Int, val sort: List<SortVO>? = null) {

    companion object Builder {
        private var builderPage = 0
        private var builderSize = 20
        private var builderSort: List<SortVO>? = null

        fun page(page: Int): PageableVO.Builder {
            this.builderPage = page
            return this
        }

        fun size(size: Int): PageableVO.Builder {
            this.builderSize = size
            return this
        }

        fun sort(sort: List<SortVO>): PageableVO.Builder {
            this.builderSort = sort
            return this
        }

        fun build() = PageableVO(builderPage, builderSize, builderSort)
    }
}

data class SortVO(val field: String, val dir: SortDirection = SortDirection.ASC) {

    companion object Builder {
        private lateinit var builderField: String
        private var builderDir = SortDirection.ASC

        fun field(field: String): SortVO.Builder {
            this.builderField = field
            return this
        }

        fun dir(dir: SortDirection): SortVO.Builder {
            this.builderDir = dir
            return this
        }

        fun build() = SortVO(builderField, builderDir)
    }
}


enum class SortDirection {
    DESC, ASC
}