package br.edu.ufpe.recife.tads.recgo.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;

import br.edu.ufpe.recife.tads.recgo.activitys.fragments.AllLocalsFragment;
import br.edu.ufpe.recife.tads.recgo.activitys.fragments.AllTravelsFragment;
import br.edu.ufpe.recife.tads.recgo.R;
import br.edu.ufpe.recife.tads.recgo.ui.adapters.ViewPageAdapter;

public class ListAllLocalsActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView closeIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_locals);

        defineViews();

        tabLayout.setupWithViewPager(viewPager);

        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(
                getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        );

        viewPageAdapter.addFragment(new AllLocalsFragment(), "Locais");
        viewPageAdapter.addFragment(new AllTravelsFragment(), "Trilhas");
        viewPager.setAdapter(viewPageAdapter);
    }

    private void defineViews(){
        tabLayout = findViewById(R.id.list_all_locals_tab);
        viewPager = findViewById(R.id.list_all_locals_viewpage);
        closeIcon = findViewById(R.id.all_list_close_icon_button);

        closeIcon.setOnClickListener(v -> {
            finish();
        });
    }
}