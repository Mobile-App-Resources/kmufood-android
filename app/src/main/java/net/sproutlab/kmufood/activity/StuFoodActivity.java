package net.sproutlab.kmufood.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import net.sproutlab.kmufood.R;
import net.sproutlab.kmufood.adapter.ShadowTransformer;
import net.sproutlab.kmufood.adapter.StulistAdapter;
import net.sproutlab.kmufood.data.Prefdata;
import net.sproutlab.kmufood.dialog.OtherFoodDialog;
import net.sproutlab.kmufood.dialog.OtherFoodInterface;

import java.util.Calendar;

public class StuFoodActivity extends AppCompatActivity implements View.OnClickListener, OtherFoodInterface {

    private Prefdata mPrefAdapter;
    private ImageButton btn_favorite;
    private boolean isFavorite = false;

    private ViewPager mViewPager;
    private StulistAdapter mAdapter;
    private ShadowTransformer mCardShadowTransformer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stufood);

        btn_favorite = (ImageButton) findViewById(R.id.btn_favorite);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        mPrefAdapter = new Prefdata(this);
        mAdapter = new StulistAdapter(this);
        mCardShadowTransformer = new ShadowTransformer(mViewPager, mAdapter);

        Calendar c = Calendar.getInstance();
        int curindex = c.get(Calendar.DAY_OF_WEEK);
        if (curindex == 1) curindex = 6;
        else curindex -= 2;

        if (mPrefAdapter.getPreferfood() == "stu") {
            btn_favorite.setImageResource(R.drawable.ic_star_on);
            isFavorite = true;
        }

        mViewPager.setAdapter(mAdapter);
        mViewPager.setPageTransformer(true, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setCurrentItem(curindex);

        btn_favorite.setOnClickListener(this);
        findViewById(R.id.btn_otherfood).setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPrefAdapter.getPreferfood() == "stu") {
            btn_favorite.setImageResource(R.drawable.ic_star_on);
            isFavorite = true;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_favorite:
                if (!isFavorite) {
                    mPrefAdapter.setPreferfood("stu");
                    btn_favorite.setImageResource(R.drawable.ic_star_on);
                    isFavorite = true;
                }
                break;
            case R.id.btn_otherfood:
                (new OtherFoodDialog(StuFoodActivity.this)).show();
                break;
        }
    }

    @Override
    public void switchAction(String foodcode) {
        switch (foodcode) {
            case "law":
                startActivity((new Intent(this, LawFoodActivity.class)).setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                break;
            case "staff":
                startActivity((new Intent(this, StaffFoodActivity.class)).setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                break;
            case "dorm":
                startActivity((new Intent(this, DormFoodActivity.class)).setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                break;
            case "chung":
                startActivity((new Intent(this, ChungFoodActivity.class)).setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                break;
        }
    }
}