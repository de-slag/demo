package de.slag.webgui.basic.call;

import java.util.Optional;
import java.util.concurrent.Callable;

import de.slag.basic.model.Token;

public interface SecureLoginCall extends Callable<Optional<Token>> {

}
