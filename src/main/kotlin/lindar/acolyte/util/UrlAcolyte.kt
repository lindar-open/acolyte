package lindar.acolyte.util

import lindar.acolyte.extensions.notNullOrBlank
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
        private val SLASH = "/"

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
        @SafeVarargs
        fun addParams(initialUrl: String, vararg params: lindar.acolyte.vo.Pair<String, String>): String {
            val trimmedUrl = initialUrl.trim()
            return validateInitialUrl(trimmedUrl).run { this + params.map { it.key + EQUAL + it.value }.joinToString(AND) }
        }

        @JvmStatic
        fun addParamsIfNotBlank(initialUrl: String, params: Map<String, String?>): String {
            val trimmedUrl = initialUrl.trim()
            return validateInitialUrl(trimmedUrl)
                    .run { this + params.filter { it.value.notNullOrBlank() }.map { it.key + EQUAL + it.value }.joinToString(AND) }
        }

        @JvmStatic
        @SafeVarargs
        fun addParamsIfNotBlank(initialUrl: String, vararg params: lindar.acolyte.vo.Pair<String, String>): String {
            val trimmedUrl = initialUrl.trim()
            return validateInitialUrl(trimmedUrl)
                    .run { this + params.filter { it.value.notNullOrBlank() }.map { it.key + EQUAL + it.value }.joinToString(AND) }
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
        fun safeConcat(initialUrl: String, vararg paths: String): String {
            var url = initialUrl
            paths.forEach { url = safeConcat(url, it) }
            return url
        }

        @JvmStatic
        fun safeConcat(initialUrl: String, path: String): String {
            val trimmedUrl = initialUrl.trim()
            val trimmedPath = path.trim()
            return if (trimmedUrl.endsWith(SLASH) && trimmedPath.startsWith(SLASH))
                trimmedUrl.removeSuffix(SLASH).plus(trimmedPath)
            else if (trimmedUrl.endsWith(SLASH) || trimmedPath.startsWith(SLASH))
                trimmedUrl.plus(trimmedPath)
            else trimmedUrl.plus(SLASH).plus(trimmedPath)
        }

        private fun buildSortPath(sort: SortVO): String {
            return SORT_PARAM + EQUAL + sort.field + COMMA + sort.dir.name
        }

        private fun validateInitialUrl(initialUrl: String): String {
            return initialUrl.removeSuffix(SLASH).run {
                if (this.contains(QUESTION))
                    this + AND
                else
                    this + QUESTION
            }
        }
    }
}