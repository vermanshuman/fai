import { Router } from 'express';

const getGenericReqParams = req => ({
  ...req.query,
  id: req.params.id,
  language: req.language,
});

const prettyUrlRouter = ({ app }) => {
  const route = Router();

  route.get('/', async (req, res) => {
    return app.render(req, res, '/', getGenericReqParams(req));
  });
  route.get('/auth/signin', async (req, res) => {
    return app.render(req, res, '/sign-in', getGenericReqParams(req));
  });
  return route;
};

export default prettyUrlRouter;
