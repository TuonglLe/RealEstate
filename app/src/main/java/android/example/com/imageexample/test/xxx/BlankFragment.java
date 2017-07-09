package android.example.com.imageexample.test.xxx;


import android.example.com.imageexample.ui.fragment.BaseFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.example.com.imageexample.R;


public class BlankFragment extends BaseFragment {


    @Override
    protected int setLayoutResource() {
        return R.layout.fragment_blank;
    }

    @Override
    protected void initializeFragment() {
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        BlackRVAdapter blackRVAdapter = new BlackRVAdapter(getContext());
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)
        );
        recyclerView.setAdapter(new OnScreenEstateRVAdapter(getContext(), null));
    }
}
