"use client";

import React, { SetStateAction, useEffect, useState } from "react";
import { IoIosArrowDown } from "react-icons/io";
import { CreateOptions } from "@/types/TroubleType";
import { BsSearch } from "react-icons/bs";
import { AiOutlineClose } from "react-icons/ai";
interface Props {
  categorys: string[];
  options: CreateOptions;
  setOptions: React.Dispatch<SetStateAction<CreateOptions>>;
  setShowOptions: React.Dispatch<SetStateAction<boolean>>;
}

export default function Options({ categorys, options, setOptions, setShowOptions }: Props) {
  const [isdrop, setIsdrop] = useState(false);
  const [tags, setTags] = useState<string[]>([]);
  const [inputText, setInputText] = useState("");
  const [isClicked, setIsClicked] = useState(false);
  const addTag = () => {
    if (!inputText) return;
    setTags((prev) => [...prev, inputText]);
    setInputText("");
  };
  useEffect(() => {
    if (isClicked) {
      setIsdrop(false);
      setIsClicked(true);
    }
  }, [isClicked]);
  return (
    <div className="w-[30rem] shadow-md bg-white p-5 rounded-lg border-2">
      <div className="flex items-center justify-between">
        <p className="font-semibold text-lg">카테고리</p>
        <div>
          <div
            defaultValue={0}
            onClick={() => setIsdrop((prev) => !prev)}
            className="relative flex justify-between items-center bg-silver h-8 rounded-lg shadow-md hover:cursor-pointer px-2 w-[20rem]"
          >
            <p>{!options.category ? "선택안함" : options.category}</p>
            <IoIosArrowDown />
            {isdrop && (
              <div className={`absolute  bg-white w-[20rem] shadow-md rounded-lg border-2 -bottom-[8rem] z-50 left-0`}>
                <div
                  className="hvc hover:cursor-pointer h-7 flex items-center px-2 rounded-t-lg"
                  onClick={() => {
                    setOptions((prev) => {
                      return { ...prev, category: "선택안함" };
                    });
                    setIsClicked(true);
                  }}
                >
                  선택안함
                </div>

                {categorys.map((category, idx) => (
                  <div
                    key={idx}
                    className={`hvc hover:cursor-pointer h-7 flex items-center px-2 ${
                      idx === categorys.length - 1 && "rounded-b-lg"
                    }`}
                    onClick={() => {
                      setOptions((prev) => {
                        return { ...prev, category };
                      });
                      setIsClicked(true);
                    }}
                  >
                    {category}
                  </div>
                ))}
              </div>
            )}
          </div>
        </div>
      </div>
      <div className="flex items-center justify-between mt-5">
        <p className="font-semibold text-lg">공개범위</p>
        <div className="flex items-center justify-between w-[20rem]">
          <label htmlFor="open" className="flex-1 items-center flex">
            공개
            <input className="ms-2" type="radio" name="scope" id="open" value={1} defaultChecked />
          </label>
          <label htmlFor="close" className="flex-1 items-center flex">
            비공개
            <input className="ms-2" type="radio" name="scope" id="close" value={0} />
          </label>
        </div>
      </div>

      <div className="flex items-center justify-between mt-5">
        <p className="font-semibold text-lg">태그 추가</p>

        <div className="flex items-center w-[20rem]">
          <div className="relative flex-1">
            <div className="absolute left-4 top-[50%] -translate-y-[50%]">
              <BsSearch />
            </div>
            <input
              className=" w-full bg-silver rounded-full border-black border border-opacity-30 h-8 shadow-md  ps-10"
              type="text"
              placeholder="태그를 입력하세요"
              value={inputText}
              onChange={({ target: { value } }) => setInputText(value)}
              onKeyUp={(e) => {
                if (e.key === "Enter") {
                  addTag();
                }
              }}
            />
          </div>
          <button
            onClick={() => {
              addTag();
            }}
            className="bg-sub text-white py-1 px-2 rounded-lg ms-2 shadow-md shadow-red-300 hover:shadow-none hover:bg-red-700"
          >
            추가
          </button>
        </div>
      </div>

      <div className="flex flex-wrap mt-2">
        {tags.map((tag, idx) => (
          <div
            key={idx}
            className="flex items-center px-2 border border-black border-opacity-25 py-1 rounded-full mt-3 shadow-md bg-silver me-2"
          >
            <p className="me-1">{tag}</p>
            <div
              className="hover:cursor-pointer hover:text-red-600"
              onClick={() => setTags(tags.filter((tagname, index) => index !== idx))}
            >
              <AiOutlineClose />
            </div>
          </div>
        ))}
      </div>
      <div className="mt-2 flex items-center justify-end gap-3">
        <button
          className="rounded-lg bg-sub text-white shadow-md py-1 px-2 hover:shadow-sm hover:bg-pink-700 transition-all duration-200"
          onClick={() => setShowOptions(false)}
        >
          닫기
        </button>
        <button className="rounded-lg bg-main shadow-md py-1 px-2 hover:shadow-sm hover:bg-amber-500 transition-all duration-200">
          업로드
        </button>
      </div>
    </div>
  );
}