package com.bithumb.tide.common.vo

import lombok.NoArgsConstructor

@NoArgsConstructor
data class TargetInfo(
    val username: String,
    val amount: Long,
)
