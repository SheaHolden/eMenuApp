package com.example.emenuapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class CategoryFragmentAdapter extends FragmentStateAdapter {

    private String menuJson;
    private ArrayList<JSONObject> categories;
    private ViewPager2 pager;
    private TabLayout tabLayout;

    public CategoryFragmentAdapter(@NonNull FragmentActivity fragmentActivity, String menuJson, ViewPager2 pager) {
        super(fragmentActivity);

        this.menuJson = menuJson;
        this.pager = pager;
        this.categories = new ArrayList<>();
        buildCategoriesAndTabs(fragmentActivity);
    }

    private void buildCategoriesAndTabs(FragmentActivity activity) {

        this.tabLayout = activity.findViewById(R.id.tabLayout);

        try {
            JSONObject menu = new JSONObject(menuJson);
            JSONArray categories = menu.getJSONArray("categories");

            for (int i = 0; i < categories.length(); i++) {
                JSONObject category = categories.getJSONObject(i);
                this.categories.add(category);
                tabLayout.addTab(tabLayout.newTab().setText(category.getString("category_name")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void initMediator() {

        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, pager,
                (tab, position) -> {
                    try {
                        JSONObject category = categories.get(position);
                        tab.setText(category.getString("category_name"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
        );
        mediator.attach();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new CategoryFragment(categories.get(position));
    }

    @Override
    public int getItemCount() {
        return this.categories.size();
    }
}
