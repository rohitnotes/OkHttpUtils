package com.example.dbz.okhttputils;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ToxicBakery.viewpager.transforms.ABaseTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.example.dbz.okhttp.NetOkHttpUtils;
import com.example.dbz.okhttp.log.Log;
import com.example.dbz.okhttp.okhttp.BaseRequest;
import com.example.dbz.okhttputils.activity.FallingActivity;
import com.example.dbz.okhttputils.activity.SnowActivity;
import com.example.dbz.okhttputils.activity.SnowTowActivity;
import com.example.dbz.okhttputils.entity.Banner;
import com.example.dbz.okhttputils.entity.Share;
import com.example.dbz.okhttputils.towview.Snow;
import com.example.dbz.okhttputils.utils.GifDataDownloader;
import com.felipecsl.gifimageview.library.GifImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String HTTPS = "https://tapp.99jr.cn/new_app/?";
    private static final String URL = HTTPS + "c=lottery&a=lottery_share";
    private static final String BANNER = HTTPS + "c=index&a=index";
    private TextView textView;
    private Button btnNet, btnActivity, btnTowActivity, btnStop, btnStart, btnFalling;
    private ConvenientBanner banner;
    private List<Banner.AderBean> mAderBean;
    private List<String> img = new ArrayList<>();
    private String transforemerName;
    private ABaseTransformer transforemer;
    private String url;
//    private Snow snow;

    public enum Transformer {
        DefaultTransformer("DefaultTransformer"),
        AccordionTransformer("AccordionTransformer"),
        BackgroundToForegroundTransformer("BackgroundToForegroundTransformer"),
        CubeInTransformer("CubeInTransformer"),
        CubeOutTransformer("CubeOutTransformer"),
        DepthPageTransformer("DepthPageTransformer"),
        FlipHorizontalTransformer("FlipHorizontalTransformer"),
        FlipVerticalTransformer("FlipVerticalTransformer"),
        ForegroundToBackgroundTransformer("ForegroundToBackgroundTransformer"),
        RotateDownTransformer("RotateDownTransformer"),
        RotateUpTransformer("RotateUpTransformer"),
        StackTransformer("StackTransformer"),
        TabletTransformer("TabletTransformer"),
        ZoomInTransformer("ZoomInTransformer"),
        ZoomOutSlideTransformer("ZoomOutSlideTransformer"),
        ZoomOutTranformer("ZoomOutTranformer");

        private final String className;

        // 构造器默认也只能是private, 从而保证构造函数只能在内部使用
        Transformer(String className) {
            this.className = className;
        }

        public String getClassName() {
            return className;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initView(){
        textView = findViewById(R.id.text);
        btnNet = findViewById(R.id.btn_net);
        btnActivity = findViewById(R.id.btn_activity);
        btnTowActivity = findViewById(R.id.btn_towactivity);
        banner = findViewById(R.id.banner);
//        snow = findViewById(R.id.snowView);
        btnStart= findViewById(R.id.btn_start);
        btnStop = findViewById(R.id.btn_stop);
        btnFalling = findViewById(R.id.btn_falling);
        btnFalling.setOnClickListener(this);
//        snow.setOnClickListener(this);
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnNet.setOnClickListener(this);
        btnActivity.setOnClickListener(this);
        btnTowActivity.setOnClickListener(this);
    }

    private void initData() {
        Map<String, String> parame = new HashMap<>();
        parame.put("usertoken", "null");
        NetOkHttpUtils.getInstance().postJsonRequest(this, BANNER, parame, Banner.class, new BaseRequest<Banner>() {
            @Override
            public void onSucceed(Banner result) throws Exception {
                Log.e("---------mAderBean = " + result.getAder());
                mAderBean = result.getAder();

                for (int i = 0; i < mAderBean.size(); i++) {
                    img.add(mAderBean.get(i).getPic());
                    url = mAderBean.get(i).getPic();
                }

                try {
                    transforemerName = Transformer.AccordionTransformer.getClassName();
                    Class cls = Class.forName("com.ToxicBakery.viewpager.transforms." + transforemerName);
                    transforemer = (ABaseTransformer) cls.newInstance();
//                    banner.setPageTransformer(transforemer);
//                    banner.getViewPager().setPageTransformer(true, transforemer);

                  //部分3D特效需要调整滑动速度
                  if (transforemerName.equals("StackTransformer")) {
                       banner.setScrollDuration(1200);
                  }

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                banner.setPages(new CBViewHolderCreator<MyViewHolder>() {
                    @Override
                    public MyViewHolder createHolder() {
                        return new MyViewHolder();
                    }
                }, img).setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                        .setPageTransformer(transforemer)
                        .startTurning(3000);
            }

            @Override
            public void onError() {
                Log.e("----------------onError");
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        banner.stopTurning();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_net:
                btnNet();
                break;
            case R.id.btn_activity:
                Intent intent = new Intent(this, SnowActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_towactivity:
                Intent intent1 = new Intent(this, SnowTowActivity.class);
                startActivity(intent1);
                break;
//            case R.id.btn_start:
//                snow.setVisibility(View.VISIBLE);
//                break;
//            case R.id.btn_stop:
//                snow.setVisibility(View.GONE);
//                break;
//            case R.id.snowView:
//                snow.setVisibility(View.GONE);
//                break;
            case R.id.btn_falling:
                Intent intent2 = new Intent(this, FallingActivity.class);
                intent2.putExtra("bitmap", url);
                startActivity(intent2);
                break;
        }
    }

    private void btnNet(){
        Map<String, String> parame = new HashMap<>();
        parame.put("usertoken", "null");
        NetOkHttpUtils.getInstance().postJsonRequest(this, URL, parame, Share.class, new BaseRequest<Share>() {
            @Override
            public void onSucceed(Share result) throws Exception {

                Log.e("result = " + result.getShare().getShare_title());
                if (result != null) {
                    textView.setText(result.getShare().getShare_title());
                }
            }

            @Override
            public void onError() {
                Log.e("----------------onError");
            }
        });
    }


    class MyViewHolder implements Holder<String> {

        private GifImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new GifImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
//            Log.e("data.length = " + data.length());
            if (data.length() > 3) {
//                Log.e("截取后三位data = " + data.substring(data.length() - 3));
                if (data.substring(data.length() - 3).equals("gif")) {
                    try {
//                        Log.e("data = " + data);
                        byte[] bytes = new GifDataDownloader().execute(data).get();
//                        Log.e("bytes = " + bytes);
                        imageView.setBytes(bytes);
                        imageView.startAnimation();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                } else {
                    Picasso.with(context).load(data).into(imageView);
                }
            }
        }
    }

}
