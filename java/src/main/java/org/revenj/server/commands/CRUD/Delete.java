package org.revenj.server.commands.CRUD;

import org.revenj.patterns.*;
import org.revenj.server.CommandResult;
import org.revenj.server.ServerCommand;
import org.revenj.server.commands.Utility;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.Optional;

public final class Delete implements ServerCommand {

	private final DomainModel domainModel;

	public Delete(DomainModel domainModel) {
		this.domainModel = domainModel;
	}

	public static final class Argument {
		public String Name;
		public String Uri;

		public Argument(String name, String uri) {
			this.Name = name;
			this.Uri = uri;
		}

		@SuppressWarnings("unused")
		private Argument() {
		}
	}

	@Override
	public <TInput, TOutput> CommandResult<TOutput> execute(ServiceLocator locator, Serialization<TInput> input, Serialization<TOutput> output, TInput data) {
		Argument arg;
		try {
			Type genericType = Utility.makeGenericType(Argument.class, data.getClass());
			arg = (Argument) input.deserialize(genericType, data);
		} catch (IOException e) {
			return CommandResult.badRequest(e.getMessage());
		}
		Optional<Class<?>> manifest = domainModel.find(arg.Name);
		if (!manifest.isPresent()) {
			return CommandResult.badRequest("Unable to find specified domain object: " + arg.Name);
		}
		PersistableRepository repository;
		try {
			repository = Utility.resolvePersistableRepository(locator, manifest.get());
		} catch (ReflectiveOperationException e) {
			return CommandResult.badRequest("Error resolving repository for: " + arg.Name + ". Reason: " + e.getMessage());
		}
		try {
			Optional<AggregateRoot> found = repository.find(arg.Uri);
			if (!found.isPresent()) {
				return CommandResult.badRequest("Can't find " + arg.Name + " with uri: " + arg.Uri);
			}
			repository.delete(found.get());
			return new CommandResult<>(output.serialize(found.get()), "Object deleted", 201);
		} catch (SQLException e) {
			return CommandResult.badRequest(e.getMessage());
		}
	}
}
