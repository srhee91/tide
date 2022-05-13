package com.bithumb.tide.source.apiclient.asset;

import com.bithumb.tide.common.vo.TakeAssetResponse;
import com.bithumb.tide.common.vo.TargetInfo;
import com.bithumb.tide.source.apiclient.advice.BithumbAsync;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "BithumbAsset", url = "${apiclient.bithumb.asset.url}", configuration = AssetFeignClientConfig.class)
public interface AssetFeignClient {
    @PostMapping(value = "/asset/bulktransfer/take", produces = "application/json")
    ResponseEntity<TakeAssetResponse> bulkTakeAsset(@RequestBody TargetInfo targetInfo);

//    @BithumbAsync
    @PostMapping(value = "/async/asset/bulktransfer/take", produces = "application/json")
    ResponseEntity bulkTakeAssetAsync(@RequestBody TargetInfo targetInfo);
}
