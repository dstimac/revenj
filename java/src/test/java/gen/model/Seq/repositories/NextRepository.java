package gen.model.Seq.repositories;



public class NextRepository   implements org.revenj.patterns.Repository<gen.model.Seq.Next>, org.revenj.patterns.PersistableRepository<gen.model.Seq.Next> {
	
	
	
	public NextRepository(
			 final java.sql.Connection connection,
			 final org.revenj.postgres.ObjectConverter<gen.model.Seq.Next> converter,
			 final org.revenj.patterns.ServiceLocator locator) {
			
		
			this.connection = connection;
		
			this.converter = converter;
		
			this.locator = locator;
	}

	private final java.sql.Connection connection;
	private final org.revenj.postgres.ObjectConverter<gen.model.Seq.Next> converter;
	private final org.revenj.patterns.ServiceLocator locator;
	
	public NextRepository(org.revenj.patterns.ServiceLocator locator) {
		this(locator.resolve(java.sql.Connection.class), new org.revenj.patterns.Generic<org.revenj.postgres.ObjectConverter<gen.model.Seq.Next>>(){}.resolve(locator), locator);
	}
	
	//@Override
	private java.util.stream.Stream<gen.model.Seq.Next> stream(java.util.Optional<org.revenj.patterns.Specification<gen.model.Seq.Next>> filter) {
		throw new UnsupportedOperationException();
	}

	private java.util.ArrayList<gen.model.Seq.Next> readFromDb(java.sql.PreparedStatement statement, java.util.ArrayList<gen.model.Seq.Next> result) throws java.sql.SQLException, java.io.IOException {
		org.revenj.postgres.PostgresReader reader = new org.revenj.postgres.PostgresReader(locator::resolve);
		try (java.sql.ResultSet rs = statement.executeQuery()) {
			while (rs.next()) {
				org.postgresql.util.PGobject pgo = (org.postgresql.util.PGobject) rs.getObject(1);
				reader.process(pgo.getValue());
				result.add(converter.from(reader));
			}
		}
		return result;
	}

	@Override
	public java.util.List<gen.model.Seq.Next> search(java.util.Optional<org.revenj.patterns.Specification<gen.model.Seq.Next>> filter, java.util.Optional<Integer> limit, java.util.Optional<Integer> offset) {
		String sql = null;
		if (filter == null || !filter.isPresent() || filter.get() == null) {
			sql = "SELECT r FROM \"Seq\".\"Next_entity\" r";
			if (limit != null && limit.isPresent() && limit.get() != null) {
				sql += " LIMIT " + Integer.toString(limit.get());
			}
			if (offset != null && offset.isPresent() && offset.get() != null) {
				sql += " OFFSET " + Integer.toString(offset.get());
			}
			try (java.sql.PreparedStatement statement = connection.prepareStatement(sql)) {
				return readFromDb(statement, new java.util.ArrayList<>());
			} catch (java.sql.SQLException | java.io.IOException e) {
				throw new RuntimeException(e);
			}
		}
		org.revenj.patterns.Specification<gen.model.Seq.Next> specification = filter.get();
		java.util.function.Consumer<java.sql.PreparedStatement> applyFilters = ps -> {};
		org.revenj.postgres.PostgresWriter pgWriter = new org.revenj.postgres.PostgresWriter();
		
		
		if (specification instanceof gen.model.Seq.Next.BetweenIds) {
			gen.model.Seq.Next.BetweenIds spec = (gen.model.Seq.Next.BetweenIds)specification;
			sql = "SELECT it FROM \"Seq\".\"Next.BetweenIds\"(?, ?) it";
			
			applyFilters = applyFilters.andThen(ps -> {
				try {
					if (spec.getMin() == null) ps.setNull(1, java.sql.Types.INTEGER); 
					else ps.setInt(1, spec.getMin());
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			});
			applyFilters = applyFilters.andThen(ps -> {
				try {
					ps.setInt(2, spec.getMax());
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			});
		}
		if (sql != null) {
			if (limit != null && limit.isPresent() && limit.get() != null) {
				sql += " LIMIT " + Integer.toString(limit.get());
			}
			if (offset != null && offset.isPresent() && offset.get() != null) {
				sql += " OFFSET " + Integer.toString(offset.get());
			}
			try (java.sql.PreparedStatement statement = connection.prepareStatement(sql)) {
				applyFilters.accept(statement);
				return readFromDb(statement, new java.util.ArrayList<>());
			} catch (java.sql.SQLException | java.io.IOException e) {
				throw new RuntimeException(e);
			}
		}
		java.util.stream.Stream<gen.model.Seq.Next> stream = stream(filter);
		if (offset != null && offset.isPresent() && offset.get() != null) {
			stream = stream.skip(offset.get());
		}
		if (limit != null && limit.isPresent() && limit.get() != null) {
			stream = stream.limit(limit.get());
		}
		return stream.collect(java.util.stream.Collectors.toList());
	}

	
	@Override
	public java.util.List<gen.model.Seq.Next> find(String[] uris) {
		try (java.sql.Statement statement = connection.createStatement()) {
			java.util.ArrayList<gen.model.Seq.Next> result = new java.util.ArrayList<>(uris.length);
			org.revenj.postgres.PostgresReader reader = new org.revenj.postgres.PostgresReader(locator::resolve);
			StringBuilder sb = new StringBuilder("SELECT r FROM \"Seq\".\"Next_entity\" r WHERE r.\"ID\" IN (");
			org.revenj.postgres.PostgresWriter.writeSimpleUriList(sb, uris);
			sb.append(")");
			try (java.sql.ResultSet rs = statement.executeQuery(sb.toString())) {
				while (rs.next()) {
					org.postgresql.util.PGobject pgo = (org.postgresql.util.PGobject) rs.getObject(1);
					reader.process(pgo.getValue());
					result.add(converter.from(reader));
				}
			}
			return result;
		} catch (java.sql.SQLException | java.io.IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public java.util.List<String> persist(
			java.util.List<gen.model.Seq.Next> insert,
			java.util.List<java.util.Map.Entry<gen.model.Seq.Next, gen.model.Seq.Next>> update,
			java.util.List<gen.model.Seq.Next> delete) throws java.sql.SQLException {
		try (java.sql.PreparedStatement statement = connection.prepareStatement("/*NO LOAD BALANCE*/SELECT * FROM \"Seq\".\"persist_Next\"(?, ?, ?, ?)")) {
			java.util.List<String> result;
			org.revenj.postgres.PostgresWriter sw = new org.revenj.postgres.PostgresWriter();
			if (insert != null && !insert.isEmpty()) {
		
				if (assignSequenceID == null) throw new RuntimeException("Next repository has not been properly set up. Static __setupSequenceID method not called");
				assignSequenceID.accept(insert, connection);
				result = new java.util.ArrayList<>(insert.size());
				org.revenj.postgres.converters.PostgresTuple tuple = org.revenj.postgres.converters.ArrayTuple.create(insert, converter::to);
				org.postgresql.util.PGobject pgo = new org.postgresql.util.PGobject();
				pgo.setType("\"Seq\".\"Next_entity\"[]");
				tuple.buildTuple(sw, false);
				pgo.setValue(sw.toString());
				sw.reset();
				statement.setObject(1, pgo);
				for (gen.model.Seq.Next it : insert) {
					String uri = gen.model.Seq.converters.NextConverter.buildURI(sw.tmp, it.getID());
					result.add(uri);
				}
			} else {
				statement.setArray(1, null);
				result = new java.util.ArrayList<>(0);
			}
			if (update != null && !update.isEmpty()) {
				java.util.List<gen.model.Seq.Next> oldUpdate = new java.util.ArrayList<>(update.size());
				java.util.List<gen.model.Seq.Next> newUpdate = new java.util.ArrayList<>(update.size());
				java.util.Map<String, Integer> missing = new java.util.HashMap<>();
				int cnt = 0;
				for (java.util.Map.Entry<gen.model.Seq.Next, gen.model.Seq.Next> it : update) {
					oldUpdate.add(it.getKey());
					if (it.getKey() == null) {
						missing.put(it.getValue().getURI(), cnt);
					}
					newUpdate.add(it.getValue());
					cnt++;
				}
				if (!missing.isEmpty()) {
					java.util.List<gen.model.Seq.Next> found = find(missing.keySet().toArray(new String[missing.size()]));
					for (gen.model.Seq.Next it : found) {
						oldUpdate.set(missing.get(it.getURI()), it);
					}
				}
				org.revenj.postgres.converters.PostgresTuple tupleOld = org.revenj.postgres.converters.ArrayTuple.create(oldUpdate, converter::to);
				org.revenj.postgres.converters.PostgresTuple tupleNew = org.revenj.postgres.converters.ArrayTuple.create(newUpdate, converter::to);
				org.postgresql.util.PGobject pgOld = new org.postgresql.util.PGobject();
				org.postgresql.util.PGobject pgNew = new org.postgresql.util.PGobject();
				pgOld.setType("\"Seq\".\"Next_entity\"[]");
				pgNew.setType("\"Seq\".\"Next_entity\"[]");
				tupleOld.buildTuple(sw, false);
				pgOld.setValue(sw.toString());
				sw.reset();
				tupleNew.buildTuple(sw, false);
				pgNew.setValue(sw.toString());
				sw.reset();
				statement.setObject(2, pgOld);
				statement.setObject(3, pgNew);
			} else {
				statement.setArray(2, null);
				statement.setArray(3, null);
			}
			if (delete != null && !delete.isEmpty()) {
				org.revenj.postgres.converters.PostgresTuple tuple = org.revenj.postgres.converters.ArrayTuple.create(delete, converter::to);
				org.postgresql.util.PGobject pgo = new org.postgresql.util.PGobject();
				pgo.setType("\"Seq\".\"Next_entity\"[]");
				tuple.buildTuple(sw, false);
				pgo.setValue(sw.toString());
				statement.setObject(4, pgo);
			} else {
				statement.setArray(4, null);
			}
			try (java.sql.ResultSet rs = statement.executeQuery()) {
				rs.next();
				String message = rs.getString(1);
				if (message != null) throw new java.sql.SQLException(message);
			}
			return result;
		} catch (java.io.IOException e) {
			throw new java.sql.SQLException(e);
		}
	}

	
	public static void __setupSequenceID(java.util.function.BiConsumer<java.util.List<gen.model.Seq.Next>, java.sql.Connection> sequence) {
		assignSequenceID = sequence;
	}

	private static java.util.function.BiConsumer<java.util.List<gen.model.Seq.Next>, java.sql.Connection> assignSequenceID;
}
