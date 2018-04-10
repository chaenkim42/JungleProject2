package com.example.seungeun.jungleproject2;

import android.app.Fragment;
import android.app.FragmentManager;

// 프레그먼트에서 프레그먼트로 이동을 하기 위한 클래스

public class BaseFragment extends Fragment {

    protected void startFragment(FragmentManager fm, Class<? extends BaseFragment> fragmentClass) {
        BaseFragment fragment = null;
        try {
            fragment = fragmentClass.newInstance();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (fragment == null) {
            throw new IllegalStateException("cannot start fragment. " + fragmentClass.getName());
        }
        getActivity().getFragmentManager().beginTransaction().add(R.id.fragment_etc, fragment).addToBackStack(null).commit();
    }

    protected void finishFragment() {
        getFragmentManager().popBackStack();
    }

    public void onPressedBackkey() {
        finishFragment();
    }
}