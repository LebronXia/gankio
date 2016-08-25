/*
 * {EasyGank}  Copyright (C) {2015}  {CaMnter}
 *
 * This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.
 * This is free software, and you are welcome to redistribute it
 * under certain conditions; type `show c' for details.
 *
 * The hypothetical commands `show w' and `show c' should show the appropriate
 * parts of the General Public License.  Of course, your program's commands
 * might be different; for a GUI interface, you would use an "about box".
 *
 * You should also get your employer (if you work as a programmer) or school,
 * if any, to sign a "copyright disclaimer" for the program, if necessary.
 * For more information on this, and how to apply and follow the GNU GPL, see
 * <http://www.gnu.org/licenses/>.
 *
 * The GNU General Public License does not permit incorporating your program
 * into proprietary programs.  If your program is a subroutine library, you
 * may consider it more useful to permit linking proprietary applications with
 * the library.  If this is what you want to do, use the GNU Lesser General
 * Public License instead of this License.  But first, please read
 * <http://www.gnu.org/philosophy/why-not-lgpl.html>.
 */

package com.xiaobozheng.gankio.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xiaobozheng.gankio.util.ToastUtils;

import butterknife.ButterKnife;
import io.realm.Realm;


/**
 * Description：BaseFragment
 * Created by：CaMnter
 * Time：2016-01-02 21:35
 */
public abstract class BaseFragment extends Fragment {

    protected View self;
    protected Realm realm;

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (this.self == null) {
            this.self = inflater.inflate(this.getLayoutId(), container, false);
        }
        if (this.self.getParent() != null) {
            ViewGroup parent = (ViewGroup) this.self.getParent();
            parent.removeView(this.self);
        }
        realm = Realm.getDefaultInstance();
        ButterKnife.bind(this,this.self);
        this.initViews(this.self, savedInstanceState);
        this.initData();
        this.initListeners();
        setupFragmentComponent();
        return this.self;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
        ButterKnife.unbind(this);
    }

    protected abstract void setupFragmentComponent();

    /**
     * Fill in layout id
     *
     * @return layout id
     */
    protected abstract int getLayoutId();

    /**
     * Initialize the view in the layout
     *
     * @param self self
     * @param savedInstanceState savedInstanceState
     */
    protected abstract void initViews(View self, Bundle savedInstanceState);

    /**
     * Initialize the View of the listener
     */
    protected abstract void initListeners();

    /**
     * Initialize the Activity data
     */
    protected abstract void initData();

    /*********
     * Toast *
     *********/

    public void showToast(String msg) {
        this.showToast(msg, Toast.LENGTH_SHORT);
    }


    public void showToast(String msg, int duration) {
        if (msg == null) return;
        if (duration == Toast.LENGTH_SHORT || duration == Toast.LENGTH_LONG) {
            ToastUtils.show(this.getActivity(), msg, duration);
        } else {
            ToastUtils.show(this.getActivity(), msg, ToastUtils.LENGTH_SHORT);
        }
    }


    public void showToast(int resId) {
        this.showToast(resId, Toast.LENGTH_SHORT);
    }


    public void showToast(int resId, int duration) {
        if (duration == Toast.LENGTH_SHORT || duration == Toast.LENGTH_LONG) {
            ToastUtils.show(this.getActivity(), resId, duration);
        } else {
            ToastUtils.show(this.getActivity(), resId, ToastUtils.LENGTH_SHORT);
        }
    }

   // public abstract void onScrollStateChanged(RecyclerView recyclerView, int newState);

    /*********
     * Toast *
     *********/

}
