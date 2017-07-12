package cn.tedu.cloud_note.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloud_note.dao.NoteDao;
import cn.tedu.cloud_note.dao.ShareDao;
import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.entity.Share;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;
@Service("shareService")
public class ShareServiceImpl implements ShareService {
	@Resource
	private ShareDao shareDao;
	
	@Resource
	private NoteDao noteDao;
	
	public NoteResult<Object> shareNote(String noteId) {
		//��cn_share���в����¼
		Share share = new Share();
		share.setCn_note_id(noteId);
		share.setCn_share_id(NoteUtil.createId());
		
		Note note = noteDao.findByNoteId(noteId);
		
		share.setCn_share_title(note.getCn_note_title());
		share.setCn_share_body(note.getCn_note_body());
		
		shareDao.save(share);
		
		NoteResult<Object> result = new NoteResult<Object>();
		result.setStatus(0);
		result.setMsg("����ɹ�");
		
		return result;
	}


	public List<Share> searchLikeNotes(String keyword) {
		List<Share> list = shareDao.searchLikeNotes(keyword);
		
		return list;
	}

}
