import { Router } from 'express';
import * as proxy from 'express-http-proxy';
import * as url from 'url';

export default () => {
  const route = Router();
  route.use('/api',
    proxy(process.env.WWW_API_SERVICE_HOST, {
      proxyReqPathResolver: req => `/api/${url.parse(req.url).path}`.replace(/\/[\/]+/g, '/').replace('/api/v1', '/api'),
    }),
  );
  return route;
};
