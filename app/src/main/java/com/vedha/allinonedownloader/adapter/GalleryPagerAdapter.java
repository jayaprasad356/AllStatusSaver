package com.vedha.allinonedownloader.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.vedha.allinonedownloader.fragment.KChinFrag;
import com.vedha.allinonedownloader.fragment.KMitronFrag;
import com.vedha.allinonedownloader.fragment.KMojFrag;
import com.vedha.allinonedownloader.fragment.KRopoFrag;
import com.vedha.allinonedownloader.fragment.KZiliFrag;
import com.vedha.allinonedownloader.fragment.LikeeFrag;
import com.vedha.allinonedownloader.fragment.MxTakFrag;
import com.vedha.allinonedownloader.fragment.SChatFrag;
import com.vedha.allinonedownloader.fragment.SnackFrag;
import com.vedha.allinonedownloader.fragment.TweatFrag;
import com.vedha.allinonedownloader.fragment.VimeoFrag;
import com.vedha.allinonedownloader.fragment.FBFrag;
import com.vedha.allinonedownloader.fragment.InsFrag;
import com.vedha.allinonedownloader.fragment.TikFrag;

public class GalleryPagerAdapter extends FragmentPagerAdapter {


    public GalleryPagerAdapter(FragmentManager fm) {
        super(fm);

    }


    @Override
    public Fragment getItem(int position) {

        if (position == 2){
            return new InsFrag();
        }  else if (position == 3){
            return new TikFrag();
        }else if (position == 4){
            return new TweatFrag();
        }else if (position == 5){
            return new FBFrag();
        }else if (position == 6){
            return new VimeoFrag();
        }else if (position == 7){
            return new LikeeFrag();
        }else if (position == 8){
            return new SnackFrag();
        }else if (position == 9){
            return new SChatFrag();
        }else if (position == 10){
            return new KRopoFrag();
        } else if (position == 11){
            return new KChinFrag();
        } else if (position == 12) {
            return new KMojFrag();
        } else if (position == 13){
            return new MxTakFrag();
        } else if (position == 14) {
            return new KMitronFrag();
        } else {
            return new KZiliFrag();
        }
    }

    @Override
    public int getCount() {
        return 12;
    }


}
