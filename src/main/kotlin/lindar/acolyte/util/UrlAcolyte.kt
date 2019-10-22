package lindar.acolyte.util

import lindar.acolyte.extensions.notNullOrBlank
import lindar.acolyte.vo.PageableVO
import lindar.acolyte.vo.SortVO

class UrlAcolyte {
    companion object {
        private const val PAGE_PARAM = "page"
        private const val SIZE_PARAM = "size"
        private const val SORT_PARAM = "sort"

        private const val AND = "&"
        private const val QUESTION = "?"
        private const val EQUAL = "="
        private const val COMMA = ","
        private const val SLASH = "/"

        private const val PATH_PARAM_LEFT = "{"
        private const val PATH_PARAM_RIGHT = "}"
        private const val PATH_PARAM_BOTH = PATH_PARAM_LEFT + PATH_PARAM_RIGHT

        /**
         * Add standard pagination params to an URL. Uses the same format as many other libraries like Spring. <br/>
         * An example would be: initialUrl?page=1&size=10&sort=createdAt,desc&sort=name,asc . <br/>
         * Yes, you can add multiple sort params, each with its own order (direction)
         */
        @JvmStatic fun addPaginationParams(initialUrl: String, pageable: PageableVO): String {
            val finalUrl = addParam(initialUrl, PAGE_PARAM, pageable.page.toString())
                    .run{addParam(this, SIZE_PARAM, pageable.size.toString())}
            return finalUrl + (pageable.sort?.let { AND + it.map { buildSortPath(it) }.joinToString(AND)} ?: "")
        }

        @JvmStatic fun addParams(initialUrl: String, params: Map<String, String>): String {
            val trimmedUrl = initialUrl.trim()
            return validateInitialUrl(trimmedUrl).run { this + params.map { it.key + EQUAL + it.value }.joinToString(AND) }
        }

        @JvmStatic @SafeVarargs fun addParams(initialUrl: String, vararg params: lindar.acolyte.vo.Pair<String, String>): String {
            val trimmedUrl = initialUrl.trim()
            return validateInitialUrl(trimmedUrl).run { this + params.map { it.key + EQUAL + it.value }.joinToString(AND) }
        }

        @JvmStatic fun addParamsIfNotBlank(initialUrl: String, params: Map<String, String?>): String {
            val trimmedUrl = initialUrl.trim()
            return validateInitialUrl(trimmedUrl)
                    .run { this + params.filter { it.value.notNullOrBlank() }.map { it.key + EQUAL + it.value }.joinToString(AND) }
        }

        @JvmStatic @SafeVarargs fun addParamsIfNotBlank(initialUrl: String, vararg params: lindar.acolyte.vo.Pair<String, String>): String {
            val trimmedUrl = initialUrl.trim()
            return validateInitialUrl(trimmedUrl)
                    .run { this + params.filter { it.value.notNullOrBlank() }.map { it.key + EQUAL + it.value }.joinToString(AND) }
        }

        @JvmStatic fun addParam(initialUrl: String, paramName: String, paramValue: String): String {
            val trimmedUrl = initialUrl.trim()
            return validateInitialUrl(trimmedUrl).run { this + paramName + EQUAL + paramValue }
        }

        @JvmStatic fun addSortParam(initialUrl: String, sort: SortVO): String {
            val trimmedUrl = initialUrl.trim()
            return validateInitialUrl(trimmedUrl).run { this + buildSortPath(sort)}
        }

        @JvmStatic fun safeConcat(initialUrl: String, vararg paths: String): String {
            var trimmedUrl = initialUrl.trim()
            paths.forEach { trimmedUrl = safeConcat(trimmedUrl, it) }
            return trimmedUrl
        }

        @JvmStatic fun safeConcat(initialUrl: String, path: String): String {
            val trimmedUrl = initialUrl.trim()
            val trimmedPath = path.trim()
            return if (trimmedUrl.endsWith(SLASH) && trimmedPath.startsWith(SLASH))
                trimmedUrl.removeSuffix(SLASH).plus(trimmedPath)
            else if (trimmedUrl.endsWith(SLASH) || trimmedPath.startsWith(SLASH))
                trimmedUrl.plus(trimmedPath)
            else trimmedUrl.plus(SLASH).plus(trimmedPath)
        }

        /**
         * Add dynamic path parameters in your URL. All you need is to use {} in your url where you want a path param value to be inserted. <br/>
         * Example for inserting an API version and user ID dynamically in the URL: http://www.example.com/api/{}/user/{}/friends <br/>
         * Now you just need to send the param values in order as either a list or array.
         * You can also replace path params by using an actual param name, this way you don't have to send the values in order.
         * For this check: {UrlAcolyte.replacePathParamsByName}
         */
        @JvmStatic fun addPathParams(initialUrl: String, vararg paramValues: String): String {
            var trimmedUrl = initialUrl.trim()
            paramValues.forEach { trimmedUrl = trimmedUrl.replaceFirst(PATH_PARAM_BOTH, it) }
            return trimmedUrl
        }

        /**
         * Add dynamic path parameters in your URL. All you need is to use {} in your url where you want a path param value to be inserted. <br/>
         * Example for inserting an API version and user ID dynamically in the URL: http://www.example.com/api/{}/user/{}/friends <br/>
         * Now you just need to send the param values in order as either a list or array.
         * You can also replace path params by using an actual param name, this way you don't have to send the values in order.
         * For this check: {UrlAcolyte.replacePathParamsByName}
         */
        @JvmStatic fun addPathParams(initialUrl: String, paramValues: List<String>): String {
            var trimmedUrl = initialUrl.trim()
            paramValues.forEach { trimmedUrl = trimmedUrl.replaceFirst(PATH_PARAM_BOTH, it) }
            return trimmedUrl
        }


        /**
         * Replace named dynamic path parameters in your URL. <br/>
         * Example for inserting an API version and user ID dynamically in the URL: http://www.example.com/api/{apiVersion}/user/{userId}/friends <br/>
         * Now you just need to send a map of params where the key is the param's name and the value is its value.
         * You can also add path params without using actual param names, just an identifier.
         * For this check: {UrlAcolyte.addPathParams}
         */
        @JvmStatic fun replacePathParamsByName(initialUrl: String, params: Map<String, String>): String {
            var trimmedUrl = initialUrl.trim()
            params.forEach { trimmedUrl = trimmedUrl.replace(PATH_PARAM_LEFT + it.key + PATH_PARAM_RIGHT, it.value.trim(), ignoreCase = true) }
            return trimmedUrl
        }

        /**
         * Replace named dynamic path parameters in your URL. <br/>
         * Example for inserting an API version and user ID dynamically in the URL: http://www.example.com/api/{apiVersion}/user/{userId}/friends <br/>
         * Now you just need to send an array (varargs) of Pair params where the key is the param's name and the value is its value.
         * You can also add path params without using actual param names, just an identifier.
         * For this check: {UrlAcolyte.addPathParams}
         */
        @JvmStatic fun replacePathParamsByName(initialUrl: String, vararg params: lindar.acolyte.vo.Pair<String, String>): String {
            var trimmedUrl = initialUrl.trim()
            params.forEach { trimmedUrl = trimmedUrl.replace(PATH_PARAM_LEFT + it.key + PATH_PARAM_RIGHT, it.value.trim(), ignoreCase = true) }
            return trimmedUrl
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
