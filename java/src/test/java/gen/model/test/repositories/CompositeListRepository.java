/*
* Created by DSL Platform
* v1.0.0.17084 
*/

package gen.model.test.repositories;



public class CompositeListRepository   implements java.io.Closeable, org.revenj.patterns.Repository<gen.model.test.CompositeList>, org.revenj.postgres.BulkRepository<gen.model.test.CompositeList> {
	
	
	
	public CompositeListRepository(
			 final java.util.Optional<java.sql.Connection> transactionContext,
			 final javax.sql.DataSource dataSource,
			 final org.revenj.postgres.QueryProvider queryProvider,
			 final gen.model.test.converters.CompositeListConverter converter,
			 final org.revenj.patterns.ServiceLocator locator) {
			
		this.transactionContext = transactionContext;
		this.dataSource = dataSource;
		this.queryProvider = queryProvider;
		this.transactionConnection = transactionContext.orElse(null);
		this.converter = converter;
		this.locator = locator;
	}

	private final java.util.Optional<java.sql.Connection> transactionContext;
	private final javax.sql.DataSource dataSource;
	private final org.revenj.postgres.QueryProvider queryProvider;
	private final java.sql.Connection transactionConnection;
	private final gen.model.test.converters.CompositeListConverter converter;
	private final org.revenj.patterns.ServiceLocator locator;
	
	private java.sql.Connection getConnection() {
		if (transactionConnection != null) return transactionConnection;
		try {
			return dataSource.getConnection();
		} catch (java.sql.SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void releaseConnection(java.sql.Connection connection) {
		if (this.transactionConnection != null) return;
		try {
			connection.close();
		} catch (java.sql.SQLException ignore) {
		}		
	}

	public CompositeListRepository(org.revenj.patterns.ServiceLocator locator) {
		this(locator.tryResolve(java.sql.Connection.class), locator.resolve(javax.sql.DataSource.class), locator.resolve(org.revenj.postgres.QueryProvider.class), locator.resolve(gen.model.test.converters.CompositeListConverter.class), locator);
	}
	

	public static org.revenj.patterns.Specification<gen.model.test.CompositeList> rewriteSpecificationToLambda(org.revenj.patterns.Specification<gen.model.test.CompositeList> filter) {
		
		if (filter instanceof gen.model.test.CompositeList.ForSimple) {
			gen.model.test.CompositeList.ForSimple _spec_ = (gen.model.test.CompositeList.ForSimple)filter;
			java.util.List<gen.model.test.Simple> _spec_simples_ = _spec_.getSimples();
			return it -> (_spec_simples_.contains(it.getSimple()));
		}
		if (filter instanceof gen.model.test.CompositeCube.FilterMax) {
			gen.model.test.CompositeCube.FilterMax _spec_ = (gen.model.test.CompositeCube.FilterMax)filter;
			java.time.LocalDate _spec_value_ = _spec_.getValue();
			return it -> it.getChange().compareTo(_spec_value_) >= 0;
		}
		return filter;
	}

	private static final boolean hasCustomSecurity = false;

	@Override
	public org.revenj.patterns.Query<gen.model.test.CompositeList> query(org.revenj.patterns.Specification<gen.model.test.CompositeList> filter) {
		org.revenj.patterns.Query<gen.model.test.CompositeList> query = queryProvider.query(transactionConnection, locator, gen.model.test.CompositeList.class);
		if (filter != null) {
			query = query.filter(rewriteSpecificationToLambda(filter));
		}
		
		return query.sortedDescendingBy(it -> it.getId());
	}

	private java.util.List<gen.model.test.CompositeList> readFromDb(java.sql.PreparedStatement statement, java.util.List<gen.model.test.CompositeList> result) throws java.sql.SQLException, java.io.IOException {
		try (java.sql.ResultSet rs = statement.executeQuery();
			org.revenj.postgres.PostgresReader reader = org.revenj.postgres.PostgresReader.create(locator)) {
			while (rs.next()) {
				reader.process(rs.getString(1));
				result.add(converter.from(reader));
			}
		}
		
		return result;
	}

	@Override
	public java.util.List<gen.model.test.CompositeList> search(org.revenj.patterns.Specification<gen.model.test.CompositeList> specification, Integer limit, Integer offset) {
		final String selectType = "SELECT it";
		java.util.function.Consumer<java.sql.PreparedStatement> applyFilters = ps -> {};
		java.sql.Connection connection = getConnection();
		try (org.revenj.postgres.PostgresWriter pgWriter = org.revenj.postgres.PostgresWriter.create()) {
			String sql;
			if (specification == null) {
				sql = "SELECT r FROM \"test\".\"CompositeList\" r";
			} 
			else if (specification instanceof gen.model.test.CompositeList.ForSimple) {
				gen.model.test.CompositeList.ForSimple spec = (gen.model.test.CompositeList.ForSimple)specification;
				sql = selectType + " FROM \"test\".\"CompositeList.ForSimple\"(?) it";
				
				applyFilters = applyFilters.andThen(ps -> {
					try {
						
				org.postgresql.util.PGobject pgo = new org.postgresql.util.PGobject();
				pgo.setType("\"test\".\"Simple\"[]");
				org.revenj.postgres.converters.PostgresTuple tuple = org.revenj.postgres.converters.ArrayTuple.create(spec.getSimples(), locator.resolve(gen.model.test.converters.SimpleConverter.class)::to);
				pgWriter.reset();
				tuple.buildTuple(pgWriter, false);
				pgo.setValue(pgWriter.toString());
				ps.setObject(1, pgo);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				});
			}
			else if (specification instanceof gen.model.test.CompositeCube.FilterMax) {
				gen.model.test.CompositeCube.FilterMax spec = (gen.model.test.CompositeCube.FilterMax)specification;
				sql = selectType + " FROM \"test\".\"CompositeList.FilterMax\"(?) it";
				
				applyFilters = applyFilters.andThen(ps -> {
					try {
						ps.setDate(1, java.sql.Date.valueOf(spec.getValue()));
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				});
			}
			else {
				org.revenj.patterns.Query<gen.model.test.CompositeList> query = query(specification);
				if (offset != null) {
					query = query.skip(offset);
				}
				if (limit != null) {
					query = query.limit(limit);
				}
				try {
					return query.list();
				} catch (java.io.IOException e) {
					throw new RuntimeException(e);
				}
			}
			sql += " ORDER BY \"id\" DESC";
			if (limit != null) {
				sql += " LIMIT " + Integer.toString(limit);
			}
			if (offset != null) {
				sql += " OFFSET " + Integer.toString(offset);
			}
			try (java.sql.PreparedStatement statement = connection.prepareStatement(sql)) {
				applyFilters.accept(statement);
				return readFromDb(statement, new java.util.ArrayList<>());
			} catch (java.sql.SQLException | java.io.IOException e) {
				throw new RuntimeException(e);
			}
		} finally {
			releaseConnection(connection);
		}
	}

	public java.util.function.BiFunction<java.sql.ResultSet, Integer, java.util.List<gen.model.test.CompositeList>> search(org.revenj.postgres.BulkReaderQuery query, org.revenj.patterns.Specification<gen.model.test.CompositeList> specification, Integer limit, Integer offset) {
		String selectType = "SELECT array_agg(_r) FROM (SELECT _it as _r";
		final org.revenj.postgres.PostgresReader rdr = query.getReader();
		final org.revenj.postgres.PostgresWriter pgWriter = query.getWriter();
		int index = query.getArgumentIndex();
		StringBuilder sb = query.getBuilder();
		if (specification == null) {
			sb.append("SELECT array_agg(_r) FROM (SELECT _r FROM \"test\".\"CompositeList\" _r");
		}
		
			else if (specification instanceof gen.model.test.CompositeList.ForSimple) {
				gen.model.test.CompositeList.ForSimple spec = (gen.model.test.CompositeList.ForSimple)specification;
				sb.append(selectType);
				sb.append(" FROM \"test\".\"CompositeList.ForSimple\"(?) it");
				
				query.addArgument(ps -> {
					try {
						
				org.postgresql.util.PGobject pgo = new org.postgresql.util.PGobject();
				pgo.setType("\"test\".\"Simple\"[]");
				org.revenj.postgres.converters.PostgresTuple tuple = org.revenj.postgres.converters.ArrayTuple.create(spec.getSimples(), locator.resolve(gen.model.test.converters.SimpleConverter.class)::to);
				pgWriter.reset();
				tuple.buildTuple(pgWriter, false);
				pgo.setValue(pgWriter.toString());
				ps.setObject(index + 1, pgo);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				});
			}
			else if (specification instanceof gen.model.test.CompositeCube.FilterMax) {
				gen.model.test.CompositeCube.FilterMax spec = (gen.model.test.CompositeCube.FilterMax)specification;
				sb.append(selectType);
				sb.append(" FROM \"test\".\"CompositeList.FilterMax\"(?) it");
				
				query.addArgument(ps -> {
					try {
						ps.setDate(index + 1, java.sql.Date.valueOf(spec.getValue()));
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				});
			}
		else {
			sb.append("SELECT 0");
			return (rs, ind) -> search(specification, limit, offset);
		}
			sb.append(" ORDER BY \"id\" DESC");
		if (limit != null && limit >= 0) {
			sb.append(" LIMIT ");
			sb.append(Integer.toString(limit));
		}
		if (offset != null && offset >= 0) {
			sb.append(" OFFSET ");
			sb.append(Integer.toString(offset));
		}
		sb.append(") _sq");
		return (rs, ind) -> {
			try {
				String res = rs.getString(ind);
				if (res == null || res.length() == 0 || res.length() == 2) {
					return new java.util.ArrayList<>(0);
				}
				rdr.process(res);
				java.util.List<gen.model.test.CompositeList> result = org.revenj.postgres.converters.ArrayTuple.parse(rdr, 0, converter::from); 
				
				return result;
			} catch (java.sql.SQLException | java.io.IOException e) {
				throw new RuntimeException(e);
			}
		};
	}

	@Override
	public long count(org.revenj.patterns.Specification<gen.model.test.CompositeList> specification) {
		final String selectType = "SELECT COUNT(*)";
		java.util.function.Consumer<java.sql.PreparedStatement> applyFilters = ps -> {};
		java.sql.Connection connection = getConnection();
		try (org.revenj.postgres.PostgresWriter pgWriter = org.revenj.postgres.PostgresWriter.create()) {
			String sql;
			if (specification == null) {
				sql = "SELECT COUNT(*) FROM \"test\".\"CompositeList\" r";
			} 
			else if (specification instanceof gen.model.test.CompositeList.ForSimple) {
				gen.model.test.CompositeList.ForSimple spec = (gen.model.test.CompositeList.ForSimple)specification;
				sql = selectType + " FROM \"test\".\"CompositeList.ForSimple\"(?) it";
				
				applyFilters = applyFilters.andThen(ps -> {
					try {
						
				org.postgresql.util.PGobject pgo = new org.postgresql.util.PGobject();
				pgo.setType("\"test\".\"Simple\"[]");
				org.revenj.postgres.converters.PostgresTuple tuple = org.revenj.postgres.converters.ArrayTuple.create(spec.getSimples(), locator.resolve(gen.model.test.converters.SimpleConverter.class)::to);
				pgWriter.reset();
				tuple.buildTuple(pgWriter, false);
				pgo.setValue(pgWriter.toString());
				ps.setObject(1, pgo);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				});
			}
			else if (specification instanceof gen.model.test.CompositeCube.FilterMax) {
				gen.model.test.CompositeCube.FilterMax spec = (gen.model.test.CompositeCube.FilterMax)specification;
				sql = selectType + " FROM \"test\".\"CompositeList.FilterMax\"(?) it";
				
				applyFilters = applyFilters.andThen(ps -> {
					try {
						ps.setDate(1, java.sql.Date.valueOf(spec.getValue()));
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				});
			}
			else {
				try {
					return query(specification).count();
				} catch (java.io.IOException e) {
					throw new RuntimeException(e);
				}
			}
			try (java.sql.PreparedStatement statement = connection.prepareStatement(sql)) {
				applyFilters.accept(statement);
				try (java.sql.ResultSet rs = statement.executeQuery()) {
					rs.next();
					return rs.getLong(1);
				}
			} catch (java.sql.SQLException e) {
				throw new RuntimeException(e);
			}
		} finally { 
			releaseConnection(connection); 
		}
	}

	public java.util.function.BiFunction<java.sql.ResultSet, Integer, Long> count(org.revenj.postgres.BulkReaderQuery query, org.revenj.patterns.Specification<gen.model.test.CompositeList> specification) {
		String selectType = "SELECT count(*)";
		final org.revenj.postgres.PostgresReader rdr = query.getReader();
		final org.revenj.postgres.PostgresWriter pgWriter = query.getWriter();
		int index = query.getArgumentIndex();
		StringBuilder sb = query.getBuilder();
		if (specification == null) {
			sb.append("SELECT count(*) FROM \"test\".\"CompositeList\" r");
		}
		
			else if (specification instanceof gen.model.test.CompositeList.ForSimple) {
				gen.model.test.CompositeList.ForSimple spec = (gen.model.test.CompositeList.ForSimple)specification;
				sb.append(selectType);
				sb.append(" FROM \"test\".\"CompositeList.ForSimple\"(?) it");
				
				query.addArgument(ps -> {
					try {
						
				org.postgresql.util.PGobject pgo = new org.postgresql.util.PGobject();
				pgo.setType("\"test\".\"Simple\"[]");
				org.revenj.postgres.converters.PostgresTuple tuple = org.revenj.postgres.converters.ArrayTuple.create(spec.getSimples(), locator.resolve(gen.model.test.converters.SimpleConverter.class)::to);
				pgWriter.reset();
				tuple.buildTuple(pgWriter, false);
				pgo.setValue(pgWriter.toString());
				ps.setObject(index + 1, pgo);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				});
			}
			else if (specification instanceof gen.model.test.CompositeCube.FilterMax) {
				gen.model.test.CompositeCube.FilterMax spec = (gen.model.test.CompositeCube.FilterMax)specification;
				sb.append(selectType);
				sb.append(" FROM \"test\".\"CompositeList.FilterMax\"(?) it");
				
				query.addArgument(ps -> {
					try {
						ps.setDate(index + 1, java.sql.Date.valueOf(spec.getValue()));
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				});
			}
		else {
			sb.append("SELECT 0");
			return (rs, ind) -> {
				try {
					return query(specification).count();
				} catch (java.io.IOException e) {
					throw new RuntimeException(e);
				}
			};
		}
		return (rs, ind) -> {
			try {
				return rs.getLong(ind);
			} catch (java.sql.SQLException e) {
				throw new RuntimeException(e);
			}
		};
	}

	@Override
	public boolean exists(org.revenj.patterns.Specification<gen.model.test.CompositeList> specification) {
		final String selectType = "SELECT exists(SELECT *";
		java.util.function.Consumer<java.sql.PreparedStatement> applyFilters = ps -> {};
		java.sql.Connection connection = getConnection();
		try (org.revenj.postgres.PostgresWriter pgWriter = org.revenj.postgres.PostgresWriter.create()) {
			String sql = null;
			if (specification == null) {
				sql = "SELECT exists(SELECT * FROM \"test\".\"CompositeList\" r";
			} 
			else if (specification instanceof gen.model.test.CompositeList.ForSimple) {
				gen.model.test.CompositeList.ForSimple spec = (gen.model.test.CompositeList.ForSimple)specification;
				sql = selectType + " FROM \"test\".\"CompositeList.ForSimple\"(?) it";
				
				applyFilters = applyFilters.andThen(ps -> {
					try {
						
				org.postgresql.util.PGobject pgo = new org.postgresql.util.PGobject();
				pgo.setType("\"test\".\"Simple\"[]");
				org.revenj.postgres.converters.PostgresTuple tuple = org.revenj.postgres.converters.ArrayTuple.create(spec.getSimples(), locator.resolve(gen.model.test.converters.SimpleConverter.class)::to);
				pgWriter.reset();
				tuple.buildTuple(pgWriter, false);
				pgo.setValue(pgWriter.toString());
				ps.setObject(1, pgo);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				});
			}
			else if (specification instanceof gen.model.test.CompositeCube.FilterMax) {
				gen.model.test.CompositeCube.FilterMax spec = (gen.model.test.CompositeCube.FilterMax)specification;
				sql = selectType + " FROM \"test\".\"CompositeList.FilterMax\"(?) it";
				
				applyFilters = applyFilters.andThen(ps -> {
					try {
						ps.setDate(1, java.sql.Date.valueOf(spec.getValue()));
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				});
			}
			else {
				try {
					return query(specification).any();
				} catch (java.io.IOException e) {
					throw new RuntimeException(e);
				}
			}
			try (java.sql.PreparedStatement statement = connection.prepareStatement(sql + ")")) {
				applyFilters.accept(statement);
				try (java.sql.ResultSet rs = statement.executeQuery()) {
					rs.next();
					return rs.getBoolean(1);
				}
			} catch (java.sql.SQLException e) {
				throw new RuntimeException(e);
			}
		} finally { 
			releaseConnection(connection); 
		}
	}

	public java.util.function.BiFunction<java.sql.ResultSet, Integer, Boolean> exists(org.revenj.postgres.BulkReaderQuery query, org.revenj.patterns.Specification<gen.model.test.CompositeList> specification) {
		String selectType = "exists(SELECT *";
		final org.revenj.postgres.PostgresReader rdr = query.getReader();
		final org.revenj.postgres.PostgresWriter pgWriter = query.getWriter();
		int index = query.getArgumentIndex();
		StringBuilder sb = query.getBuilder();
		if (specification == null) {
			sb.append("exists(SELECT * FROM \"test\".\"CompositeList\" r");
		}
		
			else if (specification instanceof gen.model.test.CompositeList.ForSimple) {
				gen.model.test.CompositeList.ForSimple spec = (gen.model.test.CompositeList.ForSimple)specification;
				sb.append(selectType);
				sb.append(" FROM \"test\".\"CompositeList.ForSimple\"(?) it");
				
				query.addArgument(ps -> {
					try {
						
				org.postgresql.util.PGobject pgo = new org.postgresql.util.PGobject();
				pgo.setType("\"test\".\"Simple\"[]");
				org.revenj.postgres.converters.PostgresTuple tuple = org.revenj.postgres.converters.ArrayTuple.create(spec.getSimples(), locator.resolve(gen.model.test.converters.SimpleConverter.class)::to);
				pgWriter.reset();
				tuple.buildTuple(pgWriter, false);
				pgo.setValue(pgWriter.toString());
				ps.setObject(index + 1, pgo);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				});
			}
			else if (specification instanceof gen.model.test.CompositeCube.FilterMax) {
				gen.model.test.CompositeCube.FilterMax spec = (gen.model.test.CompositeCube.FilterMax)specification;
				sb.append(selectType);
				sb.append(" FROM \"test\".\"CompositeList.FilterMax\"(?) it");
				
				query.addArgument(ps -> {
					try {
						ps.setDate(index + 1, java.sql.Date.valueOf(spec.getValue()));
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				});
			}
		else {
			sb.append("SELECT 0");
			return (rs, ind) -> {
				try {
					return query(specification).any();
				} catch (java.io.IOException e) {
					throw new RuntimeException(e);
				}
			};
		}
		return (rs, ind) -> {
			try {
				return rs.getBoolean(ind);
			} catch (java.sql.SQLException e) {
				throw new RuntimeException(e);
			}
		};
	}

	@Override
	public void close() throws java.io.IOException { 
	}

	
	@Override
	public java.util.List<gen.model.test.CompositeList> find(String[] uris) {
		java.sql.Connection connection = getConnection();
		try (java.sql.PreparedStatement statement = connection.prepareStatement("SELECT r FROM \"test\".\"CompositeList\" r WHERE r.\"URI\" = ANY(?)")) {
			statement.setArray(1, connection.createArrayOf("text", uris));
			return readFromDb(statement, new java.util.ArrayList<>(uris.length));
		} catch (java.sql.SQLException | java.io.IOException e) {
			throw new RuntimeException(e);
		} finally {
			releaseConnection(connection);
		}
	}

	@Override
	public java.util.function.BiFunction<java.sql.ResultSet, Integer, java.util.Optional<gen.model.test.CompositeList>> find(org.revenj.postgres.BulkReaderQuery query, String uri) {
		final org.revenj.postgres.PostgresReader rdr = query.getReader();
		StringBuilder sb = query.getBuilder();
		int index = query.getArgumentIndex();
		if (uri == null) {
			sb.append("SELECT 0");
			return (rs, ind) -> java.util.Optional.empty();
		}
		sb.append("SELECT _r FROM \"test\".\"CompositeList\" _r WHERE _r.\"URI\" = ?");
		query.addArgument(ps -> {
			try {
				ps.setString(index, uri);
			} catch (java.sql.SQLException e) {
				throw new RuntimeException(e);
			}
		});
		return (rs, ind) -> 
		{
			try {
				String res = rs.getString(ind);
				if (res == null) {
					return java.util.Optional.empty();
				}
				rdr.process(res);
				gen.model.test.CompositeList instance = converter.from(rdr);
				if (!hasCustomSecurity) return java.util.Optional.of(instance);
				java.util.List<gen.model.test.CompositeList> result = new java.util.ArrayList<>(1);
				result.add(instance);
				
				if (result.size() == 1) {
					java.util.Optional.of(instance);
				}
			} catch (java.sql.SQLException | java.io.IOException e) {
				throw new RuntimeException(e);
			}
			return java.util.Optional.empty();
		};
	}

	@Override
	public java.util.function.BiFunction<java.sql.ResultSet, Integer, java.util.List<gen.model.test.CompositeList>> find(org.revenj.postgres.BulkReaderQuery query, String[] uris) {
		final org.revenj.postgres.PostgresReader rdr = query.getReader();
		final org.revenj.postgres.PostgresWriter writer = query.getWriter();
		StringBuilder sb = query.getBuilder();
		int index = query.getArgumentIndex();
		if (uris == null || uris.length == 0) {
			sb.append("SELECT 0");
			return (rs, ind) -> new java.util.ArrayList<>(0);
		}
		sb.append("SELECT array_agg(_r) FROM \"test\".\"CompositeList\" _r WHERE _r.\"URI\" = ANY(?)");
		query.addArgument(ps -> {
			try {
				org.postgresql.util.PGobject arr = new org.postgresql.util.PGobject();
				arr.setType("text[]");
				writer.reset();
				org.revenj.postgres.converters.PostgresTuple tuple = org.revenj.postgres.converters.ArrayTuple.create(uris, org.revenj.postgres.converters.StringConverter::toTuple);
				tuple.buildTuple(writer, false);
				arr.setValue(writer.toString());
				ps.setObject(index, arr);
			} catch (java.sql.SQLException e) {
				throw new RuntimeException(e);
			}
		});
		return (rs, ind) -> 
		{
			try {
				String res = rs.getString(ind);
				if (res == null || res.length() == 0 || res.length() == 2) {
					return new java.util.ArrayList<>(0);
				}
				rdr.process(res);
				java.util.List<gen.model.test.CompositeList> result = org.revenj.postgres.converters.ArrayTuple.parse(rdr, 0, converter::from); 
				
				return result;
			} catch (java.sql.SQLException | java.io.IOException e) {
				throw new RuntimeException(e);
			}
		};
	}

}
