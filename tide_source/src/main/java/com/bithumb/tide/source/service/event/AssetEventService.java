package com.bithumb.tide.source.service.event;

import com.bithumb.tide.common.vo.TargetInfo;
import com.bithumb.tide.source.apiclient.asset.AssetFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AssetEventService {
    private final AssetFeignClient assetFeignClient;

    public String takeAsset(TargetInfo targetInfo) {
        val response = assetFeignClient.bulkTakeAsset(targetInfo);
        return Objects.requireNonNull(response.getBody()).getResult();
    }

    public ResponseEntity takeAssetAsync(TargetInfo targetInfo) {
        return assetFeignClient.bulkTakeAssetAsync(targetInfo);
    }
}
