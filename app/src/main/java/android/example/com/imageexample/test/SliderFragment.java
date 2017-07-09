package android.example.com.imageexample.test;

import android.example.com.imageexample.test.xxx.EstateDetailActivity;
import android.example.com.imageexample.ui.fragment.BaseFragment;

import android.example.com.imageexample.R;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ViewParent;

public class SliderFragment extends BaseFragment {


    private static final String LOG_TAG = SliderFragment.class.getSimpleName();

    @Override
    protected void initializeFragment() {
        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.view_pager);
        ImagePagerAdapter adapter = new ImagePagerAdapter(getContext(), R.layout.view_holder_image);
        viewPager.setAdapter(adapter);
    }

    @Override
    protected int setLayoutResource() {
        return R.layout.fragment_slider;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(LOG_TAG, getContext() instanceof EstateDetailActivity? "getContext() instanceof EstateDetailActivity" : "getContext() != EstateDetailActivity");
    }
}
