import windowHandler from 'auth0-js/src/helper/window';
import DummyStorage from 'auth0-js/src/helper/storage/dummy';
import CookieStorage from 'auth0-js/src/helper/storage/cookie';
import Warn from 'auth0-js/src/helper/warn';

function StorageHandler(options) {
  this.warn = new Warn({});
  this.storage = new CookieStorage();
  if (options.__tryLocalStorageFirst !== true) {
    return;
  }
  try {
    // some browsers throw an error when trying to access localStorage
    // when localStorage is disabled.
    var localStorage = windowHandler.getWindow().localStorage;
    if (localStorage) {
      this.storage = localStorage;
    }
  } catch (e) {
    this.warn.warning(e);
    this.warn.warning("Can't use localStorage. Using CookieStorage instead.");
  }
}

StorageHandler.prototype.failover = function() {
  if (this.storage instanceof DummyStorage) {
    this.warn.warning('DummyStorage: ignore failover');
    return;
  } else if (this.storage instanceof CookieStorage) {
    this.warn.warning('CookieStorage: failing over DummyStorage');
    this.storage = new DummyStorage();
  } else {
    this.warn.warning('LocalStorage: failing over CookieStorage');
    this.storage = new CookieStorage();
  }
};

StorageHandler.prototype.getItem = function(key) {
  try {
    return this.storage.getItem(key);
  } catch (e) {
    this.warn.warning(e);
    this.failover();
    return this.getItem(key);
  }
};

StorageHandler.prototype.removeItem = function(key) {
  try {
    return this.storage.removeItem(key);
  } catch (e) {
    this.warn.warning(e);
    this.failover();
    return this.removeItem(key);
  }
};

StorageHandler.prototype.setItem = function(key, value, options) {
  try {
    return this.storage.setItem(key, value, options);
  } catch (e) {
    this.warn.warning(e);
    this.failover();
    return this.setItem(key, value, options);
  }
};

export default StorageHandler;
