package com.bithumb.tide.destination.controller.asset;

import com.bithumb.tide.common.vo.TakeAssetResponse;
import com.bithumb.tide.common.vo.TargetInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/asset/bulktransfer")
class BulkTransferController {
    @PostMapping("/take")
    public ResponseEntity<TakeAssetResponse> bulkTakeAsset(@RequestBody TargetInfo targetInfo) throws InterruptedException {
        Thread.sleep(2000);
        return ResponseEntity.ok(
                new TakeAssetResponse("COMPLETE")
        );
    }
}
