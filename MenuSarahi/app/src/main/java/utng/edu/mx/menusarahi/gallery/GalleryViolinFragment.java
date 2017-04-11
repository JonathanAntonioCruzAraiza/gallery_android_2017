package utng.edu.mx.menusarahi.gallery;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;
import utng.edu.mx.menusarahi.R;

/**
 * Created by jony on 02/03/17.
 */



public class GalleryViolinFragment extends Fragment {
    private int currentIndex;
    private ArrayList<ImageItem> imageItems;
    private int[] photos={
            R.drawable.v,
            R.drawable.v1,
            R.drawable.v2,
            R.drawable.v3,
            R.drawable.v4,
            R.drawable.v5,
            R.drawable.v6,
            R.drawable.v7,
            R.drawable.v8,
            R.drawable.v9,
    };
    private ViewPager vpGallery;
    private Context context;
    private GalleryManager galleryManager;
    public GalleryViolinFragment() {
        this.currentIndex = 0 ;
        this.imageItems = new ArrayList();
    }//fin del metodoto GalleryGuitarrasFragment

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment_violin,container,false) ;
    }//fin metodo onCreateView

    @Override
    public void onStart() {
        super.onStart();
        context = getActivity();
        FragmentManager fragmentManager = getFragmentManager();
        galleryManager = new GalleryManager(fragmentManager);
        vpGallery=(ViewPager)getView().findViewById(R.id.vp_gallery_animals);
        for (int i = 0; i < photos.length; i++) {
            galleryManager.addFragment(FragmentImage.newInstance(photos[i]));
        }
        vpGallery.setAdapter(galleryManager);
        getActivity().onWindowFocusChanged(true);

    }//fin metodo onStart

    public class GalleryManager extends FragmentPagerAdapter {
    List<Fragment> fragments;

        public GalleryManager(FragmentManager fm) {
            super(fm);
            fragments = new ArrayList();
        }

        public void addFragment(Fragment fragment){
            fragments.add(fragment);
        }
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }//fin getItem

        @Override
        public int getCount() {
            return fragments.size();
        }//fin getCount
    }

    public static class FragmentImage extends Fragment {
        private static final String KEY_IMAGE="image";
        private int image;
        public static FragmentImage newInstance(int image){
            FragmentImage fragmentImage = new FragmentImage();
            Bundle bundle = new Bundle();
            bundle.putInt(KEY_IMAGE,image);
            fragmentImage.setArguments(bundle);
            fragmentImage.setRetainInstance(true);
            return fragmentImage;
        }//fin FragmentImage

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments()!=null){
                image=getArguments().getInt(KEY_IMAGE);
            }
        }//fin on create

        public FragmentImage(){}

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater,
                                 @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
           View rootView = inflater.inflate(R.layout.gallery_layout_violin,container,false);
            ImageView ivPhoto = (ImageView) rootView.findViewById(R.id.iv_photo_animals);
            ivPhoto.setImageResource(image);
            return rootView;
        }//fin metodo onCreateView
    }

}//fin de la clase
