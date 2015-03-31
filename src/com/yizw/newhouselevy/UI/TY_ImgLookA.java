package com.yizw.newhouselevy.UI;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cogent.core.util.ExceptionHelper;
import com.cogent.core.util.MyException;
import com.example.newhouselevy.R;

/** 查看图片，实现了对图片的放大、缩小、拖动效果*/
public class TY_ImgLookA extends Activity{
	public class KEY{	
		/** 图片路径*/
		public static final String in_imgPath = "imgPath";
	}
	public Activity activity;
	
	ImageView img;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);       
        setContentView(R.layout.ty_img_look);
		
        initControl();
        operateIntent();
    }
	
	private void initControl(){
		activity = this;		
		initBtn(R.id.btn_return);
		
		img = (ImageView) findViewById(R.id.img);
	}
	
	Button initBtn(int id) {
		Button btn = (Button) findViewById(id);
		btn.setOnClickListener(new OnClick());
		return btn;
	}
	
	private void operateIntent(){
		try {
			Intent intent = this.getIntent();
			String imgPath = intent.getStringExtra(KEY.in_imgPath);
			Bitmap bmp = BitmapFactory.decodeFile(imgPath);
			img.setImageBitmap(bmp);
		} catch (Exception e) {
			MyException myE = new MyException("查看图片出错", e.getMessage(),e.getStackTrace());
			ExceptionHelper.Operate(myE, true, activity);
		}
	}
	
	class OnClick implements View.OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {

			case R.id.btn_return:
				activity.finish();
				break;				
			}
		}
	}

}
