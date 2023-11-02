import Link from "next/link";
import Image from "next/image";
import backgroundImage from "../../../public/background/loginBackground4.jpg"

export default function page() {
  return (
    <div className="fcc w-full h-[100vh]">
      <Image src={backgroundImage} alt="backgroundImage" className="bg-cover h-screen absolute w-full -z-10"/>
      <div className="bg-white rounded p-10 text-center shadow-md">
        <h1 className="text-3xl border-b border-main">회원 가입</h1>

        <div className="my-4 text-left">
          <label className="text-gray-900">아이디(이메일)</label>
          <div className="flex">
            <input
              type="text"
              className="border block w-full p-2 rounded me-2"
              id="email"
              placeholder="이메일을 입력하세요."
            />
            <button className="bg-main text-white rounded-lg w-24 p-1">인증 요청</button>
          </div>
        </div>

        <div className="my-4 text-left">
          <label className="text-gray-900">닉네임</label>
          <input 
            type="text"
            className="border block w-full p-2 rounded me-2"
            id="nickname"
            placeholder="최대 10자까지 가능합니다."
          />
        </div>

        <div className="my-4 text-left">
          <label className="text-gray-900">비밀번호</label>
          <input 
            type="text"
            className="border block w-full p-2 rounded me-2"
            id="password"
            placeholder="최대 12자까지 가능합니다."
          />
        </div>

        <div className="my-4 text-left">
          <label className="text-gray-900">비밀번호 확인</label>
          <input 
            type="text"
            className="border block w-full p-2 rounded me-2"
            id="password"
            placeholder="최대 12자까지 가능합니다."
          />
        </div>

        <div id="submitBox" className="flex justify-around mt-6 mb-4">
          <Link
              href={"/login"}
              className="bg-slate-400 text-white py-2 inline-block w-5/12 rounded-lg"
              type="submit"
            >
              뒤로하기
          </Link>

          <button
              className="bg-main text-white py-2 inline-block w-5/12 rounded-lg"
              type="submit"
            >
              가입하기
          </button>
        </div>

      </div>
    </div>
  );
}