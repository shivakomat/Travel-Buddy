import Authentication from 'auth0-js/src/authentication';
import Management from 'auth0-js/src/management';
import WebAuth from 'auth0-js/src/web-auth';
import version from 'auth0-js/src/version';

export { Authentication, Management, WebAuth, version };

export default {
  Authentication: Authentication,
  Management: Management,
  WebAuth: WebAuth,
  version: version
};
