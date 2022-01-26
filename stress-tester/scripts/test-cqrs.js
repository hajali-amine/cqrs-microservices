import http from 'k6/http';
import { check, sleep } from "k6";

export let options = {
    stages: [
        // Ramp-up from 1 to 5 virtual users (VUs) in 5s
        { duration: "5s", target: 5 },

        // Stay at rest on 5 VUs for 10s
        { duration: "10s", target: 5 },

        // Ramp-down from 5 to 0 VUs for 5s
        { duration: "5s", target: 0 }
    ]
};

export default () => {
    const r1 = http.get("http://apigateway:8080/query/");
    check(r1, { "status is 200": (r) => r.status === 200 });

    let product = {}
    product.ref = (Math.random() + 1).toString(36).substring(7);
    product.name = (Math.random() + 1).toString(36).substring(7);
    product.description = (Math.random() + 1).toString(36).substring(7);
    product.price = Math.random();
    product.quantity = Math.round(Math.random() * 100);

    // product = {
    //     ref: product_ref,
    //     name: product_name,
    //     description: product_desc,
    //     price: product_price,
    //     quantity: product_quantity
    // }
    const r2 = http.post("http://apigateway:8080/command/create", JSON.stringify(product), {
        headers: { 'Content-Type': 'application/json' },
    });
    check(r2, { "status is 200": (r) => r.status === 200 });

    const r3 = http.post("http://apigateway:8080/command/buy/" + product.ref);
    check(r3, { "status is 200": (r) => r.status === 200 });

    const r4 = http.post("http://apigateway:8080/command/refill/" + product.ref + "?quantity=" + product.quantity);
    check(r4, { "status is 200": (r) => r.status === 200 });

    const r5 = http.get("http://apigateway:8080/query/" + product.ref);
    check(r5, { "status is 200": (r) => r.status === 200 });

    console.log(r1.status, r2.status, r3.status, r4.status, r5.status)

    sleep(.300);
};
