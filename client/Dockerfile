FROM node:18-alpine

WORKDIR /app

COPY package.json package-lock.json ./

RUN npm install -g npm@10.2.4

RUN npm install

VOLUME [ "/app/node_modules" ]

COPY . .

# build production
# RUN npm run build 

CMD [ "npm", "run", "dev" ]