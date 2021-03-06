package com.grobo.notifications.main;


import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.browser.customtabs.CustomTabsIntent;
import androidx.fragment.app.Fragment;

import com.grobo.notifications.R;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import java.util.HashMap;
import java.util.Map;

public class LinksFragment extends Fragment {

    public LinksFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_links, container, false);
        Iconify.with(new FontAwesomeModule());

        HashMap<Integer ,String> map = new HashMap<>();

        initIcons(rootView);

        map.put(R.id.tv_webmail,"https://mail.iitp.ac.in");
        map.put(R.id.tv_lib_res,"http://library.iitp.ac.in/");
        map.put(R.id.tv_lib_catalogue,"http://172.16.52.134:8380/opac/");
        map.put(R.id.tv_reg,"https://172.16.1.230/student/login2.asp");
        map.put(R.id.tv_prev_ques,"http://172.16.52.180/oldpapers");
        map.put(R.id.tv_intranet,"http://172.16.1.6/");
        map.put(R.id.tv_late_fee,"https://www.onlinesbi.com/sbicollect/icollecthome.htm");
        map.put(R.id.tv_institute_repo,"http://idr.iitp.ac.in/jspui");
        map.put(R.id.tv_inter_rel,"https://172.16.1.4/ir/");

        for(final Map.Entry<Integer, String> pair: map.entrySet()){
            LinearLayout linearLayout = rootView.findViewById(pair.getKey());
            linearLayout.setOnClickListener(v -> browserIntent(pair.getValue()));
        }

        return rootView;
    }

    private void initIcons(View rootView){
        ((ImageView)rootView.findViewById(R.id.ic_webmail)).setImageDrawable(new IconDrawable(getContext(), FontAwesomeIcons.fa_envelope).colorRes(R.color.colorAccent));
        ((ImageView)rootView.findViewById(R.id.ic_intranet)).setImageDrawable(new IconDrawable(getContext(), FontAwesomeIcons.fa_internet_explorer).colorRes(R.color.colorAccent));
        ((ImageView)rootView.findViewById(R.id.ic_reg)).setImageDrawable(new IconDrawable(getContext(), FontAwesomeIcons.fa_pencil).colorRes(R.color.colorAccent));

        ((ImageView)rootView.findViewById(R.id.ic_lib_catalog)).setImageDrawable(new IconDrawable(getContext(), FontAwesomeIcons.fa_book).colorRes(R.color.colorAccent));
        ((ImageView)rootView.findViewById(R.id.ic_lib_res)).setImageDrawable(new IconDrawable(getContext(), FontAwesomeIcons.fa_bookmark).colorRes(R.color.colorAccent));
        ((ImageView)rootView.findViewById(R.id.ic_prev_ques)).setImageDrawable(new IconDrawable(getContext(), FontAwesomeIcons.fa_copy).colorRes(R.color.colorAccent));
        ((ImageView)rootView.findViewById(R.id.ic_late_fee)).setImageDrawable(new IconDrawable(getContext(), FontAwesomeIcons.fa_money).colorRes(R.color.colorAccent));

        ((ImageView)rootView.findViewById(R.id.ic_institute_repo)).setImageDrawable(new IconDrawable(getContext(), FontAwesomeIcons.fa_institution).colorRes(R.color.colorAccent));
        ((ImageView)rootView.findViewById(R.id.ic_inter_rel)).setImageDrawable(new IconDrawable(getContext(), FontAwesomeIcons.fa_globe).colorRes(R.color.colorAccent));
    }

    private void browserIntent(String url) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(getResources().getColor(R.color.colorPrimary));

        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(getContext(), Uri.parse(url));
    }

}
