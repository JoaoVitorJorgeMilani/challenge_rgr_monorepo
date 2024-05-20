FROM node:16.20.2 as build

WORKDIR /app

COPY package*.json ./

RUN npm install

COPY . .

RUN npm run build --dev

FROM nginx:alpine

COPY --from=build /app/dist/web-transfer /usr/share/nginx/html

EXPOSE 4200

CMD ["nginx", "-g", "daemon off;"]