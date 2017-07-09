package lindar.acolyte.vo

import java.io.Serializable

data class Tag (
        val text: String,
        val color: String,
        val size: String,
        val shape: String,
        val hasArrow: Boolean
) : Serializable {
    companion object Builder {
            private var text = ""
            private var color = ""
            private var size = ""
            private var shape = ""
            private var hasArrow = false

            fun text(text: String): Tag.Builder {
                this.text = text
                return this
            }

            fun color(color: String): Tag.Builder {
                this.color = color
                return this
            }

            fun size(size: String): Tag.Builder {
                this.size = size
                return this
            }

            fun shape(shape: String): Tag.Builder {
                this.shape = shape
                return this
            }

            fun hasArrow(hasArrow: Boolean): Tag.Builder {
                this.hasArrow = hasArrow
                return this
            }

            fun build() = Tag(text, color, size, shape, hasArrow)
    }
}
