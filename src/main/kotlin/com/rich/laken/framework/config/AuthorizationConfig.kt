package com.rich.laken.framework.config

import io.smallrye.config.ConfigMapping
import io.smallrye.config.WithName

@ConfigMapping(prefix = "authorization")
interface AuthorizationConfig {
    /**
     * 无需鉴权的路径Pattern
     * @return
     */
    @WithName("permit-patterns")
    fun permitPatterns(): String?
}
