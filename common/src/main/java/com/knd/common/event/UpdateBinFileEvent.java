package com.knd.common.event;

public class UpdateBinFileEvent {
    public int code;// 0 主固件更新响应  1副固件更新响应  2传输文件响应  3文件crc校验0107  4固件更新完成0109
    public UpdateBinFileEvent(int b) {
        code = b;
    }
}
