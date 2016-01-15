package com.zncxi.huaxi.util.enumeration;

/**
 * 场景分区
 * @author xiaoCheng
 *
 */
public enum SceneGroup {
	
	ONE_SOUTH{
		@Override
		public String getTitle() {
			return "华西南区（华西一站）";
		}
	},
	
	TWO_NORTH{
		@Override
		public String getTitle() {
			return "华西北区（华西二站）";
		}
	},
	
	WEETHER_SITE{
		@Override
		public String getTitle() {
			return "气象站";
		}
	},
	
	HEADWATERS{
		@Override
		public String getTitle() {
			return "水源地";
		}
	};

	public abstract String getTitle();

	@Override
	public String toString() {
		return this.getTitle();
	}
	
	public int getValue(){
		return this.ordinal();
	}
}
