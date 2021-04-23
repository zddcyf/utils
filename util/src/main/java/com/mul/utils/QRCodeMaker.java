package com.mul.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiang on 2017-04-pic_zhutu.
 */

public class QRCodeMaker {
    /**
     * @param content 二维码内容
     * @param width   二维码宽高
     * @param fColor  颜色
     * @param bgColor 背景色
     * @return 返回bitmap
     */
    public static Bitmap makeBitmap(String content, int width, int fColor, int bgColor) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.MARGIN, 3);
        try {

            BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, width, hints);
            int[] pixels = new int[width * width];
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    int index = i * width + j;
                    if (bitMatrix.get(j, i)) {
                        pixels[index] = fColor;
                    } else {
                        pixels[index] = bgColor;
                    }
                }
            }
            return Bitmap.createBitmap(pixels, 0, width, width, width, Bitmap.Config.RGB_565);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param content    二维码内容
     * @param width      二维码宽高
     * @param fColor     颜色
     * @param bgColor    背景色
     * @param logoBitmap 中间图标
     * @return 返回bitmap
     */
    public static Bitmap makeBitmapContain(String content, int width, int fColor, int bgColor, Bitmap logoBitmap) {
        Bitmap qrBitmap = makeBitmap(content, width, fColor, bgColor);
        int qrBitmapWidth = qrBitmap.getWidth();
        int qrBitmapHeight = qrBitmap.getHeight();
        int logoBitmapWidth = logoBitmap.getWidth();
        int logoBitmapHeight = logoBitmap.getHeight();
        Bitmap blankBitmap = Bitmap.createBitmap(qrBitmapWidth, qrBitmapHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(blankBitmap);
        canvas.drawBitmap(qrBitmap, 0, 0, null);
        canvas.save();


        //设置logo背景
        float bgSx = qrBitmapWidth * 1.0f / 3.5f / logoBitmapWidth;
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        int left = (int) ((qrBitmapWidth - logoBitmapWidth * bgSx) / 2);
        int top = (int) ((qrBitmapHeight - logoBitmapHeight * bgSx) / 2);
        int right = (int) (left + logoBitmapWidth * bgSx);
        int bottom = (int) (top + logoBitmapHeight * bgSx);
        Rect bgRect = new Rect(left, top, right, bottom);
        canvas.drawRect(bgRect, paint);
        canvas.save();
        //添加logo
        float sx = qrBitmapWidth * 1.0f / 3.6f / (logoBitmapWidth);
        canvas.scale(sx, sx, qrBitmapWidth / 2, qrBitmapHeight / 2);
        canvas.drawBitmap(logoBitmap, (qrBitmapWidth - logoBitmapWidth) / 2, (qrBitmapHeight - logoBitmapHeight) / 2, null);

//        canvas.scale(sx, sx, qrBitmapWidth / 2 , qrBitmapHeight / 2);
        canvas.restore();
        return blankBitmap;
    }
}
