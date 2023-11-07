FROM node:18-alpine

WORKDIR /app

COPY package.json .

RUN npm install

VOLUME [ "/app/node_modules" ]

COPY . .

# build production
# RUN npm run build 

CMD [ "npm", "run", "dev" ]