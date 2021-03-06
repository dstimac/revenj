/*
* Created by DSL Platform
* v1.0.0.29923 
*/

package gen.model.egzotics;



public class pks   implements java.lang.Cloneable, java.io.Serializable, org.revenj.patterns.AggregateRoot, com.dslplatform.json.JsonObject {
	
	
	
	public pks() {
			
		URI = java.lang.Integer.toString(System.identityHashCode(this));
		this.id = new java.util.ArrayList<Integer>(4);
		this.pp = new java.awt.Point[] { };
		this.l = new java.awt.geom.Point2D.Double();
	}

	
	private String URI;

	
	@com.fasterxml.jackson.annotation.JsonProperty("URI")
	public String getURI()  {
		
		return this.URI;
	}

	
	@Override
	public int hashCode() {
		return URI.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null || obj instanceof pks == false)
			return false;
		final pks other = (pks) obj;
		return URI.equals(other.URI);
	}

	public boolean deepEquals(final pks other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!URI.equals(other.URI))
			return false;
		
		if(!((this.id == other.id || this.id != null && this.id.equals(other.id))))
			return false;
		if(!(this.xml == other.xml || this.xml != null && other.xml != null && this.xml.isEqualNode(other.xml)))
			return false;
		if(!(this.s3 == other.s3 || this.s3 != null && this.s3.equals(other.s3)))
			return false;
		if(!(java.util.Arrays.equals(this.pp, other.pp)))
			return false;
		if(!(this.l.equals(other.l)))
			return false;
		return true;
	}

	private pks(pks other) {
		this.URI = other.URI;
		this.__locator = other.__locator;
		this.id = new java.util.ArrayList<Integer>(other.id);
		this.xml = other.xml != null ? (org.w3c.dom.Element)other.xml.cloneNode(true) : null;
		this.s3 = other.s3;
		this.pp = new java.awt.Point[other.pp.length];
			if (other.pp != null) {
				for (int _i = 0; _i < other.pp.length; _i++) {
					this.pp[_i] = other.pp[_i] != null ? (java.awt.Point)other.pp[_i].clone() : null;
				}
			};
		this.l = other.l != null ? new java.awt.geom.Point2D.Double(other.l.getX(), other.l.getY()) : null;
		this.__originalValue = other.__originalValue;
	}

	@Override
	public Object clone() {
		return new pks(this);
	}

	@Override
	public String toString() {
		return "pks(" + URI + ')';
	}
	
	private transient java.util.Optional<org.revenj.patterns.ServiceLocator> __locator = java.util.Optional.empty();
	
	@com.fasterxml.jackson.annotation.JsonCreator private pks(
			@com.fasterxml.jackson.annotation.JsonProperty("URI") final String URI ,
			@com.fasterxml.jackson.annotation.JacksonInject("__locator") final org.revenj.patterns.ServiceLocator __locator,
			@com.fasterxml.jackson.annotation.JsonProperty("id") final java.util.List<Integer> id,
			@com.fasterxml.jackson.annotation.JsonProperty("xml") final org.w3c.dom.Element xml,
			@com.fasterxml.jackson.annotation.JsonProperty("s3") final org.revenj.storage.S3 s3,
			@com.fasterxml.jackson.annotation.JsonProperty("pp") final java.awt.Point[] pp,
			@com.fasterxml.jackson.annotation.JsonProperty("l") final java.awt.geom.Point2D l) {
		this.URI = URI != null ? URI : new java.util.UUID(0L, 0L).toString();
		this.__locator = java.util.Optional.ofNullable(__locator);
		this.id = id == null ? new java.util.ArrayList<Integer>(4) : id;
		this.xml = xml;
		this.s3 = s3;
		this.pp = pp == null ? new java.awt.Point[] { } : pp;
		this.l = l == null ? new java.awt.geom.Point2D.Double() : l;
	}

	private static final long serialVersionUID = -7167490908147075718L;
	
	private java.util.List<Integer> id;

	
	@com.fasterxml.jackson.annotation.JsonProperty("id")
	public java.util.List<Integer> getId()  {
		
		return id;
	}

	
	public pks setId(final java.util.List<Integer> value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"id\" cannot be null!");
		org.revenj.Guards.checkNulls(value);
		this.id = value;
		
		return this;
	}

	
	private org.w3c.dom.Element xml;

	
	@com.fasterxml.jackson.annotation.JsonProperty("xml")
	public org.w3c.dom.Element getXml()  {
		
		return xml;
	}

	
	public pks setXml(final org.w3c.dom.Element value) {
		
		this.xml = value;
		
		return this;
	}

	
	private org.revenj.storage.S3 s3;

	
	@com.fasterxml.jackson.annotation.JsonProperty("s3")
	public org.revenj.storage.S3 getS3()  {
		
		return s3;
	}

	
	public pks setS3(final org.revenj.storage.S3 value) {
		
		this.s3 = value;
		
		return this;
	}

	private static final java.awt.Point[] _defaultpp = new java.awt.Point[] { };
	
	private java.awt.Point[] pp;

	
	@com.fasterxml.jackson.annotation.JsonProperty("pp")
	public java.awt.Point[] getPp()  {
		
		return pp;
	}

	
	public pks setPp(final java.awt.Point[] value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"pp\" cannot be null!");
		org.revenj.Guards.checkNulls(value);
		this.pp = value;
		
		return this;
	}

	
	private java.awt.geom.Point2D l;

	
	@com.fasterxml.jackson.annotation.JsonProperty("l")
	public java.awt.geom.Point2D getL()  {
		
		return l;
	}

	
	public pks setL(final java.awt.geom.Point2D value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"l\" cannot be null!");
		this.l = value;
		
		return this;
	}

	
	static {
		gen.model.egzotics.repositories.pksRepository.__setupPersist(
			(aggregates, arg) -> {
				try {
					for (gen.model.egzotics.pks agg : aggregates) {
						 
						agg.URI = gen.model.egzotics.converters.pksConverter.buildURI(arg, agg);
					}
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			},
			(oldAggregates, newAggregates) -> {
				for (int i = 0; i < newAggregates.size(); i++) {
					gen.model.egzotics.pks oldAgg = oldAggregates.get(i);
					gen.model.egzotics.pks newAgg = newAggregates.get(i);
					 
				}
			},
			aggregates -> { 
				for (gen.model.egzotics.pks agg : aggregates) { 
				}
			},
			agg -> { 
				
		pks _res = agg.__originalValue;
		agg.__originalValue = (pks)agg.clone();
		if (_res != null) {
			return _res;
		}
				return null;
			}
		);
	}
	private transient pks __originalValue;
	
	public void serialize(final com.dslplatform.json.JsonWriter sw, final boolean minimal) {
		sw.writeByte(com.dslplatform.json.JsonWriter.OBJECT_START);
		if (minimal) {
			__serializeJsonObjectMinimal(this, sw, false);
		} else {
			__serializeJsonObjectFull(this, sw, false);
		}
		sw.writeByte(com.dslplatform.json.JsonWriter.OBJECT_END);
	}

	static void __serializeJsonObjectMinimal(final pks self, com.dslplatform.json.JsonWriter sw, boolean hasWrittenProperty) {
		
		sw.writeAscii("\"URI\":");
			com.dslplatform.json.StringConverter.serializeShort(self.URI, sw);
		
		if(self.id.size() != 0) {
			sw.writeAscii(",\"id\":[", 7);
			com.dslplatform.json.NumberConverter.serialize(self.id.get(0), sw);
			for(int i = 1; i < self.id.size(); i++) {
				sw.writeByte(com.dslplatform.json.JsonWriter.COMMA);
				com.dslplatform.json.NumberConverter.serialize(self.id.get(i), sw);
			}
			sw.writeByte(com.dslplatform.json.JsonWriter.ARRAY_END);
		}
		
			if (self.xml != null) {
				sw.writeAscii(",\"xml\":", 7);
				com.dslplatform.json.XmlConverter.serialize(self.xml, sw);
			}
		
			if (self.s3 != null) {
				sw.writeAscii(",\"s3\":", 6);
				org.revenj.json.StorageConverter.serialize(self.s3, sw);
			}
		
		if(self.pp.length != 0) {
			sw.writeAscii(",\"pp\":[", 7);
			com.dslplatform.json.JavaGeomConverter.serializePoint(self.pp[0], sw);
			for(int i = 1; i < self.pp.length; i++) {
				sw.writeByte(com.dslplatform.json.JsonWriter.COMMA);
				com.dslplatform.json.JavaGeomConverter.serializePoint(self.pp[i], sw);
			}
			sw.writeByte(com.dslplatform.json.JsonWriter.ARRAY_END);
		}
		
			if (!(self.l.getX() == 0.0 && self.l.getY() == 0.0)) {
				sw.writeAscii(",\"l\":", 5);
				com.dslplatform.json.JavaGeomConverter.serializeLocation(self.l, sw);
			}
	}

	static void __serializeJsonObjectFull(final pks self, com.dslplatform.json.JsonWriter sw, boolean hasWrittenProperty) {
		
		sw.writeAscii("\"URI\":");
			com.dslplatform.json.StringConverter.serializeShort(self.URI, sw);
		
		if(self.id.size() != 0) {
			sw.writeAscii(",\"id\":[", 7);
			com.dslplatform.json.NumberConverter.serialize(self.id.get(0), sw);
			for(int i = 1; i < self.id.size(); i++) {
				sw.writeByte(com.dslplatform.json.JsonWriter.COMMA);
				com.dslplatform.json.NumberConverter.serialize(self.id.get(i), sw);
			}
			sw.writeByte(com.dslplatform.json.JsonWriter.ARRAY_END);
		}
		else sw.writeAscii(",\"id\":[]", 8);
		
			
			if (self.xml != null) {
				sw.writeAscii(",\"xml\":", 7);
				com.dslplatform.json.XmlConverter.serialize(self.xml, sw);
			} else {
				sw.writeAscii(",\"xml\":null", 11);
			}
		
			
			if (self.s3 != null) {
				sw.writeAscii(",\"s3\":", 6);
				org.revenj.json.StorageConverter.serialize(self.s3, sw);
			} else {
				sw.writeAscii(",\"s3\":null", 10);
			}
		
		if(self.pp.length != 0) {
			sw.writeAscii(",\"pp\":[", 7);
			com.dslplatform.json.JavaGeomConverter.serializePoint(self.pp[0], sw);
			for(int i = 1; i < self.pp.length; i++) {
				sw.writeByte(com.dslplatform.json.JsonWriter.COMMA);
				com.dslplatform.json.JavaGeomConverter.serializePoint(self.pp[i], sw);
			}
			sw.writeByte(com.dslplatform.json.JsonWriter.ARRAY_END);
		}
		else sw.writeAscii(",\"pp\":[]", 8);
		
			
			sw.writeAscii(",\"l\":", 5);
			com.dslplatform.json.JavaGeomConverter.serializeLocation(self.l, sw);
	}

	public static final com.dslplatform.json.JsonReader.ReadJsonObject<pks> JSON_READER = new com.dslplatform.json.JsonReader.ReadJsonObject<pks>() {
		@Override
		public pks deserialize(final com.dslplatform.json.JsonReader reader) throws java.io.IOException {
			return new gen.model.egzotics.pks(reader);
		}
	};

	private pks(final com.dslplatform.json.JsonReader<org.revenj.patterns.ServiceLocator> reader) throws java.io.IOException {
		
		String _URI_ = "";
		this.__locator = java.util.Optional.ofNullable(reader.context);
		java.util.List<Integer> _id_ = new java.util.ArrayList<Integer>(4);
		org.w3c.dom.Element _xml_ = null;
		org.revenj.storage.S3 _s3_ = null;
		java.awt.Point[] _pp_ = _defaultpp;
		java.awt.geom.Point2D _l_ = new java.awt.geom.Point2D.Double();
		byte nextToken = reader.last();
		if(nextToken != '}') {
			int nameHash = reader.fillName();
			nextToken = reader.getNextToken();
			if(nextToken == 'n') {
				if (reader.wasNull()) {
					nextToken = reader.getNextToken();
				} else {
					throw new java.io.IOException("Expecting 'u' (as null) at position " + reader.positionInStream() + ". Found " + (char)nextToken);
				}
			} else {
				switch(nameHash) {
					
					case 2053729053:
						_URI_ = reader.readString();
				nextToken = reader.getNextToken();
						break;
					case 926444256:
						
					if (nextToken == '[') {
						nextToken = reader.getNextToken();
						if (nextToken != ']') {
							com.dslplatform.json.NumberConverter.deserializeIntCollection(reader, _id_);
						}
						nextToken = reader.getNextToken();
					}
					else throw new java.io.IOException("Expecting '[' at position " + reader.positionInStream() + ". Found " + (char)nextToken);
						break;
					case -630165834:
						_xml_ = com.dslplatform.json.XmlConverter.deserialize(reader);
					nextToken = reader.getNextToken();
						break;
					case 106018211:
						_s3_ = org.revenj.json.StorageConverter.deserializeS3(reader);
					nextToken = reader.getNextToken();
						break;
					case 1632531277:
						
					if (nextToken == '[') {
						nextToken = reader.getNextToken();
						if (nextToken != ']') {
							java.util.ArrayList<java.awt.Point> __res = com.dslplatform.json.JavaGeomConverter.deserializePointCollection(reader);
							_pp_ = __res.toArray(new java.awt.Point[__res.size()]);
						}
						nextToken = reader.getNextToken();
					}
					else throw new java.io.IOException("Expecting '[' at position " + reader.positionInStream() + ". Found " + (char)nextToken);
						break;
					case -385076981:
						_l_ = com.dslplatform.json.JavaGeomConverter.deserializeLocation(reader);
					nextToken = reader.getNextToken();
						break;
					default:
						nextToken = reader.skip();
						break;
				}
			}
			while (nextToken == ',') {
				nextToken = reader.getNextToken();
				nameHash = reader.fillName();
				nextToken = reader.getNextToken();
				if(nextToken == 'n') {
					if (reader.wasNull()) {
						nextToken = reader.getNextToken();
						continue;
					} else {
						throw new java.io.IOException("Expecting 'u' (as null) at position " + reader.positionInStream() + ". Found " + (char)nextToken);
					}
				}
				switch(nameHash) {
					
					case 2053729053:
						_URI_ = reader.readString();
				nextToken = reader.getNextToken();
						break;
					case 926444256:
						
					if (nextToken == '[') {
						nextToken = reader.getNextToken();
						if (nextToken != ']') {
							com.dslplatform.json.NumberConverter.deserializeIntCollection(reader, _id_);
						}
						nextToken = reader.getNextToken();
					}
					else throw new java.io.IOException("Expecting '[' at position " + reader.positionInStream() + ". Found " + (char)nextToken);
						break;
					case -630165834:
						_xml_ = com.dslplatform.json.XmlConverter.deserialize(reader);
					nextToken = reader.getNextToken();
						break;
					case 106018211:
						_s3_ = org.revenj.json.StorageConverter.deserializeS3(reader);
					nextToken = reader.getNextToken();
						break;
					case 1632531277:
						
					if (nextToken == '[') {
						nextToken = reader.getNextToken();
						if (nextToken != ']') {
							java.util.ArrayList<java.awt.Point> __res = com.dslplatform.json.JavaGeomConverter.deserializePointCollection(reader);
							_pp_ = __res.toArray(new java.awt.Point[__res.size()]);
						}
						nextToken = reader.getNextToken();
					}
					else throw new java.io.IOException("Expecting '[' at position " + reader.positionInStream() + ". Found " + (char)nextToken);
						break;
					case -385076981:
						_l_ = com.dslplatform.json.JavaGeomConverter.deserializeLocation(reader);
					nextToken = reader.getNextToken();
						break;
					default:
						nextToken = reader.skip();
						break;
				}
			}
			if (nextToken != '}') {
				throw new java.io.IOException("Expecting '}' at position " + reader.positionInStream() + ". Found " + (char)nextToken);
			}
		}
		
		this.URI = _URI_;
		this.id = _id_;
		this.xml = _xml_;
		this.s3 = _s3_;
		this.pp = _pp_;
		this.l = _l_;
	}

	public static Object deserialize(final com.dslplatform.json.JsonReader<org.revenj.patterns.ServiceLocator> reader) throws java.io.IOException {
		switch (reader.getNextToken()) {
			case 'n':
				if (reader.wasNull())
					return null;
				throw new java.io.IOException("Invalid null value found at: " + reader.positionInStream());
			case '{':
				reader.getNextToken();
				return new gen.model.egzotics.pks(reader);
			case '[':
				return reader.deserializeNullableCollection(JSON_READER);
			default:
				throw new java.io.IOException("Invalid char value found at: " + reader.positionInStream() + ". Expecting null, { or [. Found: " + (char)reader.last());
		}
	}
	
	public pks(org.revenj.postgres.PostgresReader reader, int context, org.revenj.postgres.ObjectConverter.Reader<pks>[] readers) throws java.io.IOException {
		this.__locator = reader.getLocator();
		for (org.revenj.postgres.ObjectConverter.Reader<pks> rdr : readers) {
			rdr.read(this, reader, context);
		}
		URI = gen.model.egzotics.converters.pksConverter.buildURI(reader, this);
		this.__originalValue = (pks)this.clone();
	}

	public static void __configureConverter(org.revenj.postgres.ObjectConverter.Reader<pks>[] readers, int __index___id, int __index___xml, int __index___s3, int __index___pp, int __index___l) {
		
		readers[__index___id] = (item, reader, context) -> { { java.util.List<Integer> __list = org.revenj.postgres.converters.IntConverter.parseCollection(reader, context, false); if(__list != null) {item.id = __list;} else item.id = new java.util.ArrayList<Integer>(4); }; return item; };
		readers[__index___xml] = (item, reader, context) -> { item.xml = org.revenj.postgres.converters.XmlConverter.parse(reader, context); return item; };
		readers[__index___s3] = (item, reader, context) -> { item.s3 = org.revenj.postgres.converters.S3Converter.parse(reader, context); return item; };
		readers[__index___pp] = (item, reader, context) -> { { java.util.List<java.awt.Point> __list = org.revenj.postgres.converters.PointConverter.parsePointCollection(reader, context, false); if(__list != null) {item.pp = __list.toArray(new java.awt.Point[__list.size()]);} else item.pp = new java.awt.Point[] { }; }; return item; };
		readers[__index___l] = (item, reader, context) -> { item.l = org.revenj.postgres.converters.PointConverter.parseLocation(reader, context, false); return item; };
	}
	
	public static void __configureConverterExtended(org.revenj.postgres.ObjectConverter.Reader<pks>[] readers, int __index__extended_id, int __index__extended_xml, int __index__extended_s3, int __index__extended_pp, int __index__extended_l) {
		
		readers[__index__extended_id] = (item, reader, context) -> { { java.util.List<Integer> __list = org.revenj.postgres.converters.IntConverter.parseCollection(reader, context, false); if(__list != null) {item.id = __list;} else item.id = new java.util.ArrayList<Integer>(4); }; return item; };
		readers[__index__extended_xml] = (item, reader, context) -> { item.xml = org.revenj.postgres.converters.XmlConverter.parse(reader, context); return item; };
		readers[__index__extended_s3] = (item, reader, context) -> { item.s3 = org.revenj.postgres.converters.S3Converter.parse(reader, context); return item; };
		readers[__index__extended_pp] = (item, reader, context) -> { { java.util.List<java.awt.Point> __list = org.revenj.postgres.converters.PointConverter.parsePointCollection(reader, context, false); if(__list != null) {item.pp = __list.toArray(new java.awt.Point[__list.size()]);} else item.pp = new java.awt.Point[] { }; }; return item; };
		readers[__index__extended_l] = (item, reader, context) -> { item.l = org.revenj.postgres.converters.PointConverter.parseLocation(reader, context, false); return item; };
	}
	
	
	public pks(
			final java.util.List<Integer> id,
			final org.w3c.dom.Element xml,
			final org.revenj.storage.S3 s3,
			final java.awt.Point[] pp,
			final java.awt.geom.Point2D l) {
			
		URI = java.lang.Integer.toString(System.identityHashCode(this));
		setId(id);
		setXml(xml);
		setS3(s3);
		setPp(pp);
		setL(l);
	}

}
