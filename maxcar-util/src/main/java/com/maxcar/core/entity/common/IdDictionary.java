package com.maxcar.core.entity.common;

public class IdDictionary {
          
	      private Integer id;
	      
	      private String market_id;
	      
	      private String table_name;
	      
	      private String pkid;
	      
	      private Integer version;

	      
		public IdDictionary() {
		   super();
		}

		public IdDictionary(String market_id, String table_name) {
			super();
			this.market_id = market_id;
			this.table_name = table_name;
		}

		public IdDictionary(Integer id, String market_id, String table_name,
				String pkid, Integer version) {
			super();
			this.id = id;
			this.market_id = market_id;
			this.table_name = table_name;
			this.pkid = pkid;
			this.version = version;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getMarket_id() {
			return market_id;
		}

		public void setMarket_id(String market_id) {
			this.market_id = market_id;
		}

		public String getTable_name() {
			return table_name;
		}

		public void setTable_name(String table_name) {
			this.table_name = table_name;
		}

		public String getPkid() {
			return pkid;
		}

		public void setPkid(String pkid) {
			this.pkid = pkid;
		}

		public Integer getVersion() {
			return version;
		}

		public void setVersion(Integer version) {
			this.version = version;
		}
	      
	      
		
		
}
