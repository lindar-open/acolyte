package lindar.acolyte.vo

import java.io.Serializable

data class Tag(
        val text: String?,
        val color: String?,
        val size: String?,
        val shape: String?,
        val icon: String?,
        val hasArrow: Boolean,
        val isBasic: Boolean,
        val isTag: Boolean,
        val isImage: Boolean,
        val isAvatar: Boolean,
        val imageUrl: String?,
        val tooltip: String?,
        val detail: String?,
        val isPointing: Boolean,
        val pointingPos: String?,
        val isHorizontal: Boolean,
        val isFloating: Boolean,
        val actionTrigger: String?
              ) : Serializable {
    companion object Builder {
        private var text = ""
        private var color = ""
        private var size = ""
        private var shape = ""
        private var icon = ""
        private var hasArrow = false
        private var isBasic = false
        private var isTag = false
        private var isImage = false
        private var isAvatar = false
        private var imageUrl = ""
        private var tooltip = ""
        private var detail = ""
        private var isPointing = false
        private var pointingPos = ""
        private var isHorizontal = false
        private var isFloating = false
        private var action = ""

        fun text(text: String?): Tag.Builder {
            this.text = text.orEmpty()
            return this
        }

        fun color(color: String?): Tag.Builder {
            this.color = color.orEmpty()
            return this
        }

        fun size(size: String?): Tag.Builder {
            this.size = size.orEmpty()
            return this
        }

        fun icon(icon: String?): Tag.Builder {
            this.icon = icon.orEmpty()
            return this
        }

        fun shape(shape: String?): Tag.Builder {
            this.shape = shape.orEmpty()
            return this
        }

        fun hasArrow(hasArrow: Boolean?): Tag.Builder {
            this.hasArrow = hasArrow ?: false
            return this
        }

        fun isBasic(isBasic: Boolean?): Tag.Builder {
            this.isBasic = isBasic ?: false
            return this
        }

        fun isTag(isTag: Boolean?): Tag.Builder {
            this.isTag = isTag ?: false
            return this
        }

        fun isImage(isImage: Boolean?): Tag.Builder {
            this.isImage = isImage ?: false
            return this
        }

        fun isAvatar(isAvatar: Boolean?): Tag.Builder {
            this.isAvatar = isAvatar ?: false
            return this
        }

        fun imageUrl(imageUrl: String?): Tag.Builder {
            this.imageUrl = imageUrl.orEmpty()
            return this
        }

        fun tooltip(tooltip: String?): Tag.Builder {
            this.tooltip = tooltip.orEmpty()
            return this
        }

        fun detail(detail: String?): Tag.Builder {
            this.detail = detail.orEmpty()
            return this
        }

        fun isPointing(isPointing: Boolean?): Tag.Builder {
            this.isPointing = isPointing ?: false
            return this
        }

        fun pointingPos(pointingPos: String?): Tag.Builder {
            this.pointingPos = pointingPos.orEmpty()
            return this
        }

        fun isHorizontal(isHorizontal: Boolean?): Tag.Builder {
            this.isHorizontal = isHorizontal ?: false
            return this
        }

        fun isFloating(isFloating: Boolean?): Tag.Builder {
            this.isFloating = isFloating ?: false
            return this
        }

        fun action(action: String?): Tag.Builder {
            this.action = action.orEmpty()
            return this
        }

        fun build() = Tag(text, color, size, shape, icon, hasArrow, isBasic, isTag, isImage, isAvatar, imageUrl, tooltip, detail, isPointing, pointingPos, isHorizontal, isFloating, action)
    }
}
