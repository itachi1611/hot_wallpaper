package com.fox.wallpaper.ui.intro;

public class IntroPresenter implements IntroContract.Presenter {

	private IntroContract.View mView;

	public IntroPresenter(IntroContract.View mView) {
		this.mView = mView;
	}

}
