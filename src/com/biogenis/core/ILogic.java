package com.biogenis.core;

public interface ILogic {
	public void init() throws Exception;
	public void update();
	public void render();
	public void dispose();
}
