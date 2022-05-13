package com.bithumb.tide.source.controller.event;

import com.bithumb.tide.common.vo.TakeAssetResponse;
import com.bithumb.tide.common.vo.TargetInfo;
import com.bithumb.tide.source.service.event.AssetEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class AssetEventController {
    private final AssetEventService assetEventService;

    @PostMapping("/event/asset/take")
    public ResponseEntity<TakeAssetResponse> takeAsset(@RequestBody TargetInfo targetInfo) {
        return ResponseEntity.ok(
                TakeAssetResponse.builder()
                        .result(assetEventService.takeAsset(targetInfo))
                        .build()
        );
    }
    @PostMapping("/async/event/asset/take")
    public ResponseEntity takeAssetAsync(@RequestBody TargetInfo targetInfo) {
        return assetEventService.takeAssetAsync(targetInfo);
    }
}
