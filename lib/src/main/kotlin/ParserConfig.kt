package dev.hossain.timeline

/**
 * Configuration for the timeline parser with sensible defaults.
 * 
 * @property enableLazyLoading Whether to enable lazy loading for performance optimization.
 * @property validateInput Whether to validate input before parsing.
 */
data class ParserConfig(
    val enableLazyLoading: Boolean = true,
    val validateInput: Boolean = true
) {
    companion object {
        /**
         * Default configuration with optimal settings.
         */
        val DEFAULT = ParserConfig()
        
        /**
         * Performance-optimized configuration.
         */
        val PERFORMANCE = ParserConfig(
            enableLazyLoading = true,
            validateInput = false
        )
    }
}