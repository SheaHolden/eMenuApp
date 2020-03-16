package com.example.emenuapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.airbnb.epoxy.EpoxyRecyclerView;
import com.example.emenuapp.epoxy.MenuListController;
import org.json.JSONObject;


/**
 * This fragment contains the recycler view for a specific menu category.
 * It is managed by the CategoryFragmentAdapter and ViewPager2.
 */
public class CategoryFragment extends Fragment {

    private JSONObject category;
    private EpoxyRecyclerView recyclerView;
    private MenuListController controller;

    public CategoryFragment(JSONObject category) {
        super();

        this.category = category;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.category_page, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.categoryRecycler);
        controller = new MenuListController();
        recyclerView.setController(controller);

        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), LinearLayout.VERTICAL);
        divider.setDrawable(getContext().getDrawable(R.drawable.divider));

        recyclerView.addItemDecoration(divider);
        controller.setData(category);
    }
}
