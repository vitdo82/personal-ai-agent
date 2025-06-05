import React from "react";
import { Card, CardContent } from "@/components/ui/card";
import { Button } from "@/components/ui/button";

const items = Array.from({ length: 10 }, (_, i) => `Item ${i + 1}`);

const ItemList: React.FC = () => {
    return (
        <div className="max-w-md mx-auto p-4">
        <Card>
            <CardContent className="p-4">
            <h2 className="text-xl font-bold mb-4">Item List</h2>
            <ul className="space-y-2">
                {
                    items.map((item, index) => (
                        <li key={index} className="p-2 bg-gray-100 rounded-lg flex justify-between items-center">
                            {item}
                            <Button variant="outline" size="sm">Action</Button>
                        </li>
                    ))
                }
            </ul>
            </CardContent>
        </Card>
      </div>
    );
};

export default ItemList;
