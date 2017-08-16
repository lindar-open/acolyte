package lindar.acolyte.util

import lindar.acolyte.vo.PageableVO
import lindar.acolyte.vo.SortVO

class UrlAcolyte {
    companion object {
        private val PAGE_PARAM = "page"
        private val SIZE_PARAM = "size"
        private val SORT_PARAM = "sort"

        private val AND = "&"
        private val QUESTION = "?"
        private val EQUAL = "="
        private val COMMA = ","
        private val FOR_SLASH = "/"

        @JvmStatic
        fun addPaginationParams(initialUrl: String, pageable: PageableVO): String {
            val finalUrl = addParam(initialUrl, PAGE_PARAM, pageable.page.toString())
                    .run{addParam(this, SIZE_PARAM, pageable.size.toString())}
            return finalUrl + (pageable.sort?.let { AND + it.map { buildSortPath(it) }.joinToString(AND)} ?: "")
        }

        @JvmStatic
        fun addParams(initialUrl: String, params: Map<String, String>): String {
            val trimmedUrl = initialUrl.trim()
            return validateInitialUrl(trimmedUrl).run { this + params.map { it.key + EQUAL + it.value }.joinToString(AND) }
        }

        @JvmStatic
        fun addParam(initialUrl: String, paramName: String, paramValue: String): String {
            val trimmedUrl = initialUrl.trim()
            return validateInitialUrl(trimmedUrl).run { this + paramName + EQUAL + paramValue }
        }

        @JvmStatic
        fun addSortParam(initialUrl: String, sort: SortVO): String {
            val trimmedUrl = initialUrl.trim()
            return validateInitialUrl(trimmedUrl).run { this + buildSortPath(sort)}
        }

        @JvmStatic
        fun safeConcat(initialUrl: String, path: String): String {
            val trimmedUrl = initialUrl.trim()
            val trimmedPath = path.trim()
            return if (trimmedUrl.endsWith(FOR_SLASH) && trimmedPath.startsWith(FOR_SLASH))
                trimmedUrl.removeSuffix(FOR_SLASH).plus(trimmedPath)
            else trimmedUrl.plus(trimmedPath)
        }

        private fun buildSortPath(sort: SortVO): String {
            return SORT_PARAM + EQUAL + sort.field + COMMA + sort.dir.name
        }

        private fun validateInitialUrl(initialUrl: String): String {
            return initialUrl.removeSuffix(FOR_SLASH).run {
                if (this.contains(QUESTION))
                    this + AND
                else
                    this + QUESTION
            }
        }
    }
}